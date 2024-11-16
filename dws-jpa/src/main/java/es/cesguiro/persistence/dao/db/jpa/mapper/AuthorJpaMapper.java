package es.cesguiro.persistence.dao.db.jpa.mapper;

import es.cesguiro.domain.model.Author;
import es.cesguiro.persistence.dao.db.jpa.entity.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorJpaMapper {

    AuthorJpaMapper INSTANCE = Mappers.getMapper(AuthorJpaMapper.class);

    Author toAuthor(AuthorEntity authorEntity);

    AuthorEntity toAuthorEntity(Author author);
}
