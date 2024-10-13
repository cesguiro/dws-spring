package es.cesguiro.domain.user.model.mapper;

import es.cesguiro.domain.user.model.command.AuthorCommand;
import es.cesguiro.domain.user.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    @Mapping(target="biographyEs", source = "biography")
    @Mapping(target="biographyEn", source = "biography")
    AuthorCommand toAuthorCommand(Author author);
}
