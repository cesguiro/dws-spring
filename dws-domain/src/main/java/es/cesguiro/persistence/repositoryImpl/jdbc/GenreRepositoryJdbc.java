package es.cesguiro.persistence.repositoryImpl.jdbc;

import es.cesguiro.domain.model.command.GenreCommand;
import es.cesguiro.domain.model.query.GenreQuery;
import es.cesguiro.domain.repository.GenreRepository;
import es.cesguiro.persistence.repositoryImpl.jdbc.mapper.GenreRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryJdbc implements GenreRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<GenreQuery> getByIsbnBook(String isbn) {
        String sql = """
                SELECT genreQueries.* FROM genreQueries
                JOIN books_genres ON genreQueries.id = books_genres.genre_id
                JOIN books ON books_genres.book_id = books.id
                AND books.isbn = ?
           """;
        return jdbcTemplate.query(sql, new GenreRowMapper(),isbn);
    }

    @Override
    public void insert(GenreCommand genreCommand) {
        String sql = """
                    INSERT INTO genres(name_es, name_en, slug) 
                    VAULES(?, ?, ?)
                """;
        jdbcTemplate.update(sql, genreCommand.getName_es(), genreCommand.getName_en(), genreCommand.getSlug());
    }
}
