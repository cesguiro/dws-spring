package es.cesguiro.persistence.repositoryImpl.jdbc;

import es.cesguiro.domain.model.command.BookCommand;
import es.cesguiro.domain.model.query.BookQuery;
import es.cesguiro.domain.repository.AuthorRepository;
import es.cesguiro.domain.repository.BookRepository;
import es.cesguiro.domain.repository.GenreRepository;
import es.cesguiro.persistence.repositoryImpl.jdbc.mapper.BookRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJdbc implements BookRepository {

    private final JdbcTemplate jdbcTemplate;

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public List<BookQuery> getAll()
    {
        String sql = """
                        SELECT * FROM books
                     """;
        return jdbcTemplate.query(sql, new BookRowMapper());
    }

    @Override
    public Optional<BookQuery> findByIsbn(String isbn) {
        String sql = """
                SELECT * FROM books
                LEFT JOIN categories ON books.category_id = categories.id
                LEFT JOIN publishers ON books.publisher_id = publishers.id
                WHERE books.isbn = ?
           """;
        try {
            BookQuery bookQuery = jdbcTemplate.queryForObject(sql, new BookRowMapper(), isbn);
            bookQuery.setAuthors(authorRepository.getByIsbnBook(isbn));
            bookQuery.setGenres(genreRepository.getByIsbnBook(isbn));
            return Optional.of(bookQuery);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void addGenre(String isbn, long id) {
        String sql = """
                INSERT INTO books_genres (book_id, genre_id) VALUES ((SELECT id FROM books WHERE isbn = ?), ?)
           """;
        jdbcTemplate.update(sql, isbn, id);
    }

    @Override
    public void addAuthor(String isbn, long authorId) {
        String sql = """
                INSERT INTO books_authors (book_id, author_id) VALUES ((SELECT id FROM books WHERE isbn = ?), ?)
           """;
        jdbcTemplate.update(sql, isbn, authorId);
    }

    @Override
    public Optional<BookQuery> insert(BookCommand bookCommand) {
        String sql = """
                INSERT INTO books (isbn, title_es, title_en, synopsis_es, synopsis_en, price, discount, cover, publisher_id, category_id)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
              """;
        jdbcTemplate.update(sql,
                bookCommand.getIsbn(),
                bookCommand.getTitleEs(),
                bookCommand.getTitleEn(),
                bookCommand.getSynopsisEs(),
                bookCommand.getSynopsisEn(),
                bookCommand.getPrice(),
                bookCommand.getDiscount(),
                bookCommand.getCover(),
                bookCommand.getPublisher().getId(),
                bookCommand.getCategory().getId()
        );
        return this.findByIsbn(bookCommand.getIsbn());
    }
}
