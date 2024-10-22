package es.cesguiro.persistence.user.repository.impl.jdbc.mapper;

import es.cesguiro.common.locale.LanguageUtils;
import es.cesguiro.domain.user.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

@RequiredArgsConstructor
public class GenreRowMapper implements RowMapper<Genre> {

    @Override
    public Genre mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        String language = LanguageUtils.getCurrentLanguage();
        Genre genre = new Genre();
        genre.setName(resultSet.getString("genres.name_" + language));
        genre.setSlug(resultSet.getString("genres.slug"));
        return genre;
    }
}
