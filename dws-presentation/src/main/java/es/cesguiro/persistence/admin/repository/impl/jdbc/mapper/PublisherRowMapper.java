package es.cesguiro.persistence.admin.repository.impl.jdbc.mapper;

import es.cesguiro.domain.admin.model.Publisher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PublisherRowMapper implements RowMapper<Publisher> {

    @Override
    public Publisher mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Publisher publisher = new Publisher();
        publisher.setId(resultSet.getInt("id"));
        publisher.setName(resultSet.getString("name"));
        publisher.setSlug(resultSet.getString("slug"));
        return publisher;
    }
}