package es.cesguiro.persistence.dao.db.impl.jdbc.mapper;

import es.cesguiro.domain.model.Genre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreRowMapper implements RowMapper<Genre> {
    @Override
    public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
        Genre genre = new Genre();
        genre.setId(rs.getLong("genres.id"));
        genre.setNameEs(rs.getString("genres.name_es"));
        genre.setNameEn(rs.getString("genres.name_en"));
        genre.setSlug(rs.getString("genres.slug"));
        return genre;
    }
}
