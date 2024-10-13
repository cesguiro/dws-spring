package es.cesguiro.persistence.admin.repository.impl.jdbc.mapper;

import es.cesguiro.domain.admin.model.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRowMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getInt("id"));
        author.setName(resultSet.getString("name"));
        author.setNationality(resultSet.getString("nationality"));
        author.setBiographyEs(resultSet.getString("biography_es"));
        author.setBiographyEn(resultSet.getString("biography_en"));
        author.setBirthYear(resultSet.getInt("birth_year"));
        author.setDeathYear(resultSet.getInt("death_year"));
        return author;
    }
}
