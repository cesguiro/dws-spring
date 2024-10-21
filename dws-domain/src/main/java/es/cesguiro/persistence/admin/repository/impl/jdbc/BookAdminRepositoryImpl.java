package es.cesguiro.persistence.admin.repository.impl.jdbc;

import es.cesguiro.domain.admin.model.Author;
import es.cesguiro.domain.admin.model.Book;
import es.cesguiro.domain.admin.model.Genre;
import es.cesguiro.domain.admin.repository.AuthorAdminRepository;
import es.cesguiro.domain.admin.repository.BookAdminRepository;
import es.cesguiro.domain.admin.repository.GenreAdminRepository;
import es.cesguiro.persistence.admin.repository.impl.jdbc.mapper.BookRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookAdminRepositoryImpl implements BookAdminRepository {

    private final JdbcTemplate jdbcTemplate;

    private final AuthorAdminRepository authorAdminRepository;
    private final GenreAdminRepository genreAdminRepository;

    @Override
    public List<Book> getAll() {
        String sql = """
                        SELECT * FROM books
                     """;
        return jdbcTemplate.query(sql, new BookRowMapper());
    }

    @Override
    public List<Book> getAll(int page, int size) {
        String sql = """
                        SELECT * FROM books
                        LIMIT ? OFFSET ?
                     """;
        return jdbcTemplate.query(sql, new BookRowMapper(), size, page * size);
    }

    @Override
    public int count() {
        String sql = """
                        SELECT COUNT(*) FROM books
                     """;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        String sql = """
                SELECT * FROM books
                LEFT JOIN categories ON books.category_id = categories.id
                LEFT JOIN publishers ON books.publisher_id = publishers.id
                WHERE books.isbn = ?
           """;
        try {
            Book book = jdbcTemplate.queryForObject(sql, new BookRowMapper(), isbn);
            book.setAuthors(authorAdminRepository.getByIsbnBook(isbn));
            book.setGenres(genreAdminRepository.getByIsbnBook(isbn));
            return Optional.of(book);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Book> findById(int id) {
        String sql = """
                SELECT * FROM books
                LEFT JOIN categories ON books.category_id = categories.id
                LEFT JOIN publishers ON books.publisher_id = publishers.id
                WHERE books.id = ?
           """;
        try {
            Book book = jdbcTemplate.queryForObject(sql, new BookRowMapper(), id);
            book.setAuthors(authorAdminRepository.getByIdBook(id));
            book.setGenres(genreAdminRepository.getByIdBook(id));
            return Optional.of(book);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Book book) {
        //Si el id existe, actualizar, si no, instalar
        if(book.getId() != null) {
            update(book);
        } else {
            long id = insert(book);
            book.setId(id);
        }
        this.deleteAuthors(book.getId());
        this.insertAuthors(book.getId(), book.getAuthors());
        this.deleteGenres(book.getId());
        this.insertGenres(book.getId(), book.getGenres());
    }

    private void update(Book book) {
        String sql = """
                    UPDATE books
                    SET isbn = ?,
                        title_es = ?,
                        title_en = ?,
                        synopsis_es = ?,
                        synopsis_en = ?,
                        price = ?,
                        discount = ?,
                        cover = ?,
                        publisher_id = ?,
                        category_id = ?
                    WHERE id = ?
                """;
        jdbcTemplate.update(
                sql,
                book.getIsbn(),
                book.getTitleEs(),
                book.getTitleEn(),
                book.getSynopsisEs(),
                book.getSynopsisEn(),
                book.getPrice(),
                book.getDiscount(),
                book.getCover(),
                book.getPublisher().getId(),
                book.getCategory().getId(),
                book.getId()
        );
    }

    private long insert(Book book) {
        String sql = """
                    INSERT INTO books(
                      isbn, 
                      title_es, 
                      title_en, 
                      synopsis_es, 
                      synopsis_en, 
                      price, 
                      discount, 
                      cover, 
                      publisher_id, 
                      category_id)
                    VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id"});
            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getTitleEs());
            ps.setString(3, book.getTitleEn());
            ps.setString(4, book.getSynopsisEs());
            ps.setString(5, book.getSynopsisEn());
            ps.setBigDecimal(6, book.getPrice());
            ps.setFloat(7, book.getDiscount());
            ps.setString(8, book.getCover());
            ps.setLong(9, book.getPublisher().getId());
            ps.setLong(10, book.getCategory().getId());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue(); // Devuelve el id generado
    }

    private void deleteAuthors(long id) {
        String sql = """
                    DELETE FROM books_authors
                    WHERE book_id = ?
                """;
        jdbcTemplate.update(sql, id);
    }

    private void insertAuthors(long id, List<Author> authors) {
        String sql = """
                    INSERT INTO books_authors(book_id, author_id)
                    VALUES (?, ?)
                """;
        authors.stream().forEach(a -> jdbcTemplate.update(sql, id, a.getId()));
    }

    private void deleteGenres(long id) {
        String sql = """
                    DELETE FROM books_genres
                    WHERE book_id = ?
                """;
        jdbcTemplate.update(sql, id);
    }

    private void insertGenres(long id, List<Genre> genres) {
        String sql = """
                    INSERT INTO books_genres(book_id, genre_id)
                    VALUES(?, ?)
                """;
        genres.stream().forEach(g -> jdbcTemplate.update(sql, id, g.getId()));
    }
}
