package es.cesguiro.persistence.user.repository.impl.jdbc.mapper;

import es.cesguiro.common.locale.LanguageUtils;
import es.cesguiro.domain.user.model.Category;
import es.cesguiro.persistence.common.CustomRowMapper;
import lombok.RequiredArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class CategoryRowMapper implements CustomRowMapper<Category> {

    @Override
    public Category mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        String language = LanguageUtils.getCurrentLanguage();
        Category category = new Category();
        category.setName(resultSet.getString("categories.name_" + language));
        category.setSlug(resultSet.getString("categories.slug"));
        return category;
    }
}
