package es.cesguiro.persistence.repositoryImpl.jdbc.mapper;


import es.cesguiro.domain.model.query.PublisherQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PublisherRowMapper implements CustomRowMapper<PublisherQuery> {


    @Override
    public PublisherQuery mapRow(ResultSet rs, int rowNum) throws SQLException {
        PublisherQuery publisherQuery = new PublisherQuery();
        publisherQuery.setId(rs.getInt("publishers.id"));
        publisherQuery.setName(rs.getString("publishers.name"));
        publisherQuery.setSlug(rs.getString("publishers.slug"));
        return publisherQuery;
    }
}
