package es.cesguiro.persistence.admin.repository.impl.jdbc;

import es.cesguiro.domain.admin.model.Book;
import es.cesguiro.domain.admin.repository.AuthorAdminRepository;
import es.cesguiro.domain.admin.repository.BookAdminRepository;
import es.cesguiro.domain.admin.repository.GenreAdminRepository;
import es.cesguiro.persistence.admin.repository.impl.jdbc.mapper.BookRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
            insert(book);
        }
        //eliminar todos los autores
        //guardar los autores
        //eliminar todos los géneros
        //guardar los géneros
    }

    private void update(Book book) {
    }

    private void insert(Book book) {
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
        jdbcTemplate.update(
                sql,
                book.getIsbn(),
                book.getTitleEs(),
                book.getTitleEn(),
                book.getSynopsisEs(), book.getSynopsisEn())
    }
}
