package es.cesguiro.persistence.repository.impl.jdbc;

import es.cesguiro.domain.model.Book;
import es.cesguiro.persistence.repository.AuthorRepository;
import es.cesguiro.persistence.repository.BookRepository;
import es.cesguiro.persistence.repository.GenreRepository;
import es.cesguiro.persistence.repository.impl.jdbc.mapper.BookRowMapper;
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
    public List<Book> getAll()
    {
        String sql = """
                        SELECT * FROM books
                     """;
        return jdbcTemplate.query(sql, new BookRowMapper());
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
            book.setAuthors(authorRepository.getByIsbnBook(isbn));
            book.setGenres(genreRepository.getByIsbnBook(isbn));
            return Optional.of(book);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
