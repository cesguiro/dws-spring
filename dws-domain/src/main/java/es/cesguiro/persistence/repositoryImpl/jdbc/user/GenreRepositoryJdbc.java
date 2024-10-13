package es.cesguiro.persistence.repositoryImpl.jdbc.user;

import es.cesguiro.domain.user.model.command.GenreCommand;
import es.cesguiro.domain.user.model.Genre;
import es.cesguiro.domain.user.repository.GenreRepository;
import es.cesguiro.persistence.repositoryImpl.jdbc.user.mapper.GenreRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryJdbc implements GenreRepository {

    private final JdbcTemplate jdbcTemplate;

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
    public void insert(GenreCommand genreCommand) {
        String sql = """
                    INSERT INTO genres(name_es, name_en, slug) 
                    VALUES(?, ?, ?)
                """;
        jdbcTemplate.update(sql, genreCommand.getNameEs(), genreCommand.getNameEn(), genreCommand.getSlug());
    }

    @Override
    public Optional<Genre> findBySlug(String slug) {
        String sql = """
                    SELECT * FROM genres WHERE genres.slug = ?
                """;
        try {
            Genre genre = jdbcTemplate.queryForObject(sql, new GenreRowMapper(),slug);
            return Optional.of(genre);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
