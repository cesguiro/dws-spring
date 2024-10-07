package es.cesguiro.persistence.repositoryImpl.jdbc.mapper;

import es.cesguiro.domain.model.query.GenreQuery;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreRowMapper implements RowMapper<GenreQuery> {


    @Override
    public GenreQuery mapRow(ResultSet rs, int rowNum) throws SQLException {
        GenreQuery genreQuery = new GenreQuery();
        genreQuery.setId(rs.getInt("id"));
        genreQuery.setName(rs.getString("name_es"));
        genreQuery.setSlug(rs.getString("slug"));
        return genreQuery;
    }
}
