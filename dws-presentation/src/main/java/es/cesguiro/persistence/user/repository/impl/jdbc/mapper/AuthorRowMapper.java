package es.cesguiro.persistence.user.repository.impl.jdbc.mapper;

import es.cesguiro.common.locale.LanguageUtils;
import es.cesguiro.domain.user.model.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

@RequiredArgsConstructor
public class AuthorRowMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        String language = LanguageUtils.getCurrentLanguage();
        Author author = new Author();
        author.setId(resultSet.getInt("authors.id"));
        author.setName(resultSet.getString("authors.name"));
        author.setNationality(resultSet.getString("authors.nationality"));
        author.setBiography(resultSet.getString("authors.biography_" + language));
        author.setBirthYear(resultSet.getInt("authors.birth_year"));
        author.setDeathYear(resultSet.getInt("authors.death_year"));
        return author;
    }
}
