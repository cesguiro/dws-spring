package es.cesguiro.persistence.dao.db.impl.jdbc;

import es.cesguiro.domain.model.Author;
import es.cesguiro.domain.model.ListWithCount;
import es.cesguiro.persistence.dao.db.AuthorDaoDb;
import es.cesguiro.persistence.dao.db.impl.jdbc.mapper.AuthorRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository("authorDaoJdbc")
//@Primary
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDaoDb {

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
    public List<Author> getByIdBook(long idBook) {
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


    @Override
    public List<Author> getAll() {
        //TODO: Implementar obtener todos los autores
        return List.of();
    }

    @Override
    public ListWithCount<Author> getAll(int page, int size) {
        //TODO: Implementar obtener todos los autores paginados
        return new ListWithCount<>(List.of(), 0);
    }

    @Override
    public Optional<Author> findById(long id) {
        //TODO: Implementar obtener un autor por id
        return Optional.empty();
    }

    @Override
    public long insert(Author author) {
        //TODO: Implementar insertar un autor
        return 0;
    }

    @Override
    public void update(Author author) {
        //TODO: Implementar actualizar un autor
    }

    @Override
    public void delete(long id) {
        //TODO: Implementar borrar un autor
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Author save(Author author) {
        author.setId(this.insert(author));
        return author;
    }
}
