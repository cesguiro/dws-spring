package es.cesguiro.persistence.dao.db.impl.jpa.mapper;

import es.cesguiro.domain.model.Genre;
import es.cesguiro.persistence.dao.db.impl.jpa.entity.GenreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GenreJpaMapper {

    GenreJpaMapper INSTANCE = Mappers.getMapper(GenreJpaMapper.class);

    Genre toGenre(GenreEntity genreEntity);

    GenreEntity toGenreEntity(Genre genre);
}
