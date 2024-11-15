package es.cesguiro.domain.usecase.book.user.mapper;

import es.cesguiro.domain.model.Author;
import es.cesguiro.domain.usecase.book.user.model.AuthorUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorUser toAuthorUser(Author Author);

}
