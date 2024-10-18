package es.cesguiro.persistence.user.repository.impl.jdbc;

import es.cesguiro.domain.user.model.Book;
import es.cesguiro.domain.user.repository.AuthorUserRepository;
import es.cesguiro.domain.user.repository.BookUserRepository;
import es.cesguiro.domain.user.repository.GenreUserRepository;
import es.cesguiro.persistence.user.repository.impl.jdbc.mapper.BookRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookUserRepositoryJdbc implements BookUserRepository {

    private final JdbcTemplate jdbcTemplate;

    private final AuthorUserRepository authorUserRepository;
    private final GenreUserRepository genreUserRepository;

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
            book.setAuthors(authorUserRepository.getByIsbnBook(isbn));
            book.setGenres(genreUserRepository.getByIsbnBook(isbn));
            return Optional.of(book);
        } catch (Exception e) {
            return Optional.empty();
        }
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
}
