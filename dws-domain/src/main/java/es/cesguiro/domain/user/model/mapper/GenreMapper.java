package es.cesguiro.domain.user.model.mapper;

import es.cesguiro.domain.user.model.command.GenreCommand;
import es.cesguiro.domain.user.model.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GenreMapper {

    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    @Mapping(target = "nameEs", source = "name")
    @Mapping(target = "nameEn", source = "name")
    GenreCommand toGenreCommand(Genre genre);
}
