package es.cesguiro.persistence.repositoryImpl.jdbc;

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
}
