package es.cesguiro.persistence.admin.repository.impl.jdbc.mapper;

import es.cesguiro.domain.admin.model.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRowMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getLong("authors.id"));
        author.setName(resultSet.getString("authors.name"));
        author.setNationality(resultSet.getString("authors.nationality"));
        author.setBiographyEs(resultSet.getString("authors.biography_es"));
        author.setBiographyEn(resultSet.getString("authors.biography_en"));
        author.setBirthYear(resultSet.getInt("authors.birth_year"));
        author.setDeathYear(resultSet.getInt("authors.death_year"));
        return author;
    }
}
