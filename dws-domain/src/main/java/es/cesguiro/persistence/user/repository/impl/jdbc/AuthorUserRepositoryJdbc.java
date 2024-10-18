package es.cesguiro.persistence.user.repository.impl.jdbc;

import es.cesguiro.domain.user.model.Author;
import es.cesguiro.domain.user.repository.AuthorUserRepository;
import es.cesguiro.persistence.user.repository.impl.jdbc.mapper.AuthorRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorUserRepositoryJdbc implements AuthorUserRepository {

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
}
