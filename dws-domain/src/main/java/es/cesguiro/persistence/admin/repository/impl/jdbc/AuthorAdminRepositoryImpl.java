package es.cesguiro.persistence.admin.repository.impl.jdbc;

import es.cesguiro.domain.admin.model.Author;
import es.cesguiro.domain.admin.repository.AuthorAdminRepository;
import es.cesguiro.persistence.admin.repository.impl.jdbc.mapper.AuthorRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AuthorAdminRepositoryImpl implements AuthorAdminRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
    public List<Author> getByIdBook(int idBook) {
        String sql = """
                SELECT authors.* FROM authors
                JOIN books_authors ON authors.id = books_authors.author_id
                AND books_authors.book_id = ?
           """;
        return jdbcTemplate.query(sql, new AuthorRowMapper(), idBook);
    }

    @Override
    public List<Author> findAllById(Long[] ids) {
        String sql = """
               SELECT authors.* FROM authors
               WHERE id IN (:ids)   
           """;
        Map<String, List<Long>> params = Map.of("ids", Arrays.asList(ids));
        return namedParameterJdbcTemplate.query(sql, params, new AuthorRowMapper());
    }
}