package es.cesguiro.controller.webModel.author;

import es.cesguiro.domain.model.query.AuthorQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorCollection toAuthorCollection(AuthorQuery authorQuery);
}
