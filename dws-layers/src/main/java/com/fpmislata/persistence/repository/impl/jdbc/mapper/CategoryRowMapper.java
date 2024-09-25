package com.fpmislata.persistence.repository.impl.jdbc.mapper;

import com.fpmislata.domain.model.Category;
import org.springframework.jdbc.core.RowMapper;

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
