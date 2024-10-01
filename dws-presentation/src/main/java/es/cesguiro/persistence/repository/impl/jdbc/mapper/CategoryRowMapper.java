package es.cesguiro.persistence.repository.impl.jdbc.mapper;

import es.cesguiro.domain.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements CustomRowMapper<Category> {


    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();
        category.setId(rs.getInt("categories.id"));
        category.setName(rs.getString("categories.name_es"));
        category.setSlug(rs.getString("categories.slug"));
        return category;
    }
}
