package es.cesguiro.persistence.dao.db.impl.jdbc.mapper;

import es.cesguiro.domain.model.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<Category> {

    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();
        category.setId(rs.getLong("categories.id"));
        category.setNameEs(rs.getString("categories.name_es"));
        category.setNameEn(rs.getString("categories.name_en"));
        category.setSlug(rs.getString("categories.slug"));
        return category;
    }
}
