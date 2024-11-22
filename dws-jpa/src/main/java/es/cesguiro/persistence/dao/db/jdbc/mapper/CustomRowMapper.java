package es.cesguiro.persistence.dao.db.jdbc.mapper;

import es.cesguiro.common.locale.LanguageUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomRowMapper<T> extends RowMapper<T> {

    //String language = LanguageUtils.getCurrentLanguage();

    default boolean existsColumn(ResultSet rs, String columnName) {
        try {
            rs.findColumn(columnName);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    default String getLanguage() {
        return LanguageUtils.getCurrentLanguage();
    }

}
