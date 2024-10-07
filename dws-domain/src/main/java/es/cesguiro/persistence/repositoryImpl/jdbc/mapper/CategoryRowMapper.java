package es.cesguiro.persistence.repositoryImpl.jdbc.mapper;

import es.cesguiro.domain.model.query.CategoryQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements CustomRowMapper<CategoryQuery> {


    @Override
    public CategoryQuery mapRow(ResultSet rs, int rowNum) throws SQLException {
        CategoryQuery categoryQuery = new CategoryQuery();
        categoryQuery.setId(rs.getInt("categories.id"));
        categoryQuery.setName(rs.getString("categories.name_es"));
        categoryQuery.setSlug(rs.getString("categories.slug"));
        return categoryQuery;
    }
}
