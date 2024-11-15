package es.cesguiro.domain.usecase.book.user.mapper;

import es.cesguiro.domain.model.Genre;
import es.cesguiro.domain.usecase.book.user.model.GenreUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GenreMapper {

    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    GenreUser toGenreUser(Genre genre);

}
