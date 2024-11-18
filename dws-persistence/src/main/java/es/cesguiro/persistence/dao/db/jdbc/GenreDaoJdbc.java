package es.cesguiro.persistence.dao.db.jdbc;

import es.cesguiro.domain.model.Book;
import es.cesguiro.domain.model.Genre;
import es.cesguiro.persistence.dao.db.GenreDaoDb;
import es.cesguiro.persistence.dao.db.jdbc.mapper.GenreRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDaoDb {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Genre> getByIsbnBook(String isbn) {
        String sql = """
                SELECT genres.* FROM genres
                JOIN books_genres ON genres.id = books_genres.genre_id
                JOIN books ON books_genres.book_id = books.id
                AND books.isbn = ?
           """;
        return jdbcTemplate.query(sql, new GenreRowMapper(),isbn);
    }

    @Override
    public List<Genre> getByIdBook(long idBook) {
        String sql = """
                SELECT genres.* FROM genres
                JOIN books_genres ON genres.id = books_genres.genre_id
                AND books_genres.book_id = ?
           """;
        return jdbcTemplate.query(sql, new GenreRowMapper(),idBook);
    }

    @Override
    public List<Genre> findAllById(Long[] ids) {
        String sql = """
                SELECT * FROM genres
                WHERE id IN (:ids)
           """;
        Map<String, List<Long>> params = Map.of("ids", Arrays.asList(ids));
        return namedParameterJdbcTemplate.query(sql, params, new GenreRowMapper());
    }

    @Override
    public List<Genre> getAll() {
        //TODO: Implementar obtener todas los generos
        return List.of();
    }

    @Override
    public List<Genre> getAll(int page, int size) {
        //TODO: Implementar obtener todas los generos paginados
        return List.of();
    }

    @Override
    public Optional<Genre> findById(long id) {
        //TODO: Implementar obtener un genero por id
        return Optional.empty();
    }

    @Override
    public long insert(Genre genre) {
        //TODO: Implementar insertar un genero
        return 0;
    }

    @Override
    public void update(Genre genre) {
        //TODO: Implementar actualizar un genero
    }

    @Override
    public void delete(long id) {
        //TODO: Implementar borrar un genero
    }

    @Override
    public int count() {
        //TODO: Implementar contar los generos
        return 0;
    }

    @Override
    public Genre save(Genre genre) {
        //TODO: Implementar guardar un genero
        return null;
    }
}
