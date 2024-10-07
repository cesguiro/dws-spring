package es.cesguiro.persistence.repositoryImpl.jdbc.mapper;

import es.cesguiro.domain.model.query.AuthorQuery;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRowMapper implements RowMapper<AuthorQuery> {
    @Override
    public AuthorQuery mapRow(ResultSet rs, int rowNum) throws SQLException {
        AuthorQuery authorQuery = new AuthorQuery();
        authorQuery.setId(rs.getInt("id"));
        authorQuery.setName(rs.getString("name"));
        authorQuery.setNationality(rs.getString("nationality"));
        authorQuery.setBiography(rs.getString("biography_es"));
        authorQuery.setBirthYear(rs.getInt("birth_year"));
        authorQuery.setDeathYear(rs.getInt("death_year"));
        return authorQuery;
    }
}
