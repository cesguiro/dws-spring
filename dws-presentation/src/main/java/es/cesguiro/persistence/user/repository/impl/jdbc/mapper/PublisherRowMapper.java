package es.cesguiro.persistence.user.repository.impl.jdbc.mapper;


import es.cesguiro.domain.user.model.Publisher;
import es.cesguiro.persistence.common.CustomRowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class PublisherRowMapper implements CustomRowMapper<Publisher> {


    @Override
    public Publisher mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Publisher publisher = new Publisher();
        publisher.setName(resultSet.getString("publishers.name"));
        publisher.setSlug(resultSet.getString("publishers.slug"));
        return publisher;
    }
}
