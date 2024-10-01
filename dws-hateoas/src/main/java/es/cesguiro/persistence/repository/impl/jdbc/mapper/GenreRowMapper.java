package es.cesguiro.persistence.repository.impl.jdbc.mapper;

import es.cesguiro.domain.model.Genre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreRowMapper implements RowMapper<Genre> {


    @Override
    public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
        Genre genre = new Genre();
        genre.setId(rs.getInt("id"));
        genre.setName(rs.getString("name_es"));
        genre.setSlug(rs.getString("slug"));
        return genre;
    }
}
