package es.cesguiro.controller.webModel.publisher;

import es.cesguiro.domain.model.query.PublisherQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PublisherMapper {

    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);

    PublisherCollection toPublisherCollection(PublisherQuery publisherQuery);
}
