package es.cesguiro.persistence.repository.impl.jdbc;

import es.cesguiro.domain.model.Genre;
import es.cesguiro.persistence.repository.GenreRepository;
import es.cesguiro.persistence.repository.impl.jdbc.mapper.GenreRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryJdbc implements GenreRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Genre> getByIsbnBook(String isbn) {
        String sql = """
                SELECT genreQueries.* FROM genreQueries
                JOIN books_genres ON genreQueries.id = books_genres.genre_id
                JOIN books ON books_genres.book_id = books.id
                AND books.isbn = ?
           """;
        return jdbcTemplate.query(sql, new GenreRowMapper(),isbn);
    }
}
