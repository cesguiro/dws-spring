package es.cesguiro.persistence.repositoryImpl.jdbc.user;

import es.cesguiro.domain.user.model.Author;
import es.cesguiro.domain.user.repository.AuthorRepository;
import es.cesguiro.persistence.repositoryImpl.jdbc.user.mapper.AuthorRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryJdbc implements AuthorRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Author> getByIsbnBook(String isbn) {
        String sql = """
                SELECT authors.* FROM authors
                JOIN books_authors ON authors.id = books_authors.author_id
                JOIN books ON books_authors.book_id = books.id
                AND books.isbn = ?
           """;
        return jdbcTemplate.query(sql, new AuthorRowMapper(), isbn);
    }

    @Override
    public Optional<Author> findById(long id) {
        String sql = """
                SELECT * FROM authors WHERE id = ?
           """;
        try {
            Author author = jdbcTemplate.queryForObject(sql, new AuthorRowMapper(), id);
            return Optional.of(author);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
