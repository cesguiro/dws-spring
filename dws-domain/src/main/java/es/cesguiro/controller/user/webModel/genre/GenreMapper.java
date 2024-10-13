package es.cesguiro.controller.user.webModel.genre;

import es.cesguiro.domain.user.model.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GenreMapper {

    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    List<String> toGenreNameList(List<Genre> genreQueries);

    default String toGenreName(Genre genre) {
        return genre.getName();
    }
}