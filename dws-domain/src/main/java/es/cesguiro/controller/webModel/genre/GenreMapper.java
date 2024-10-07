package es.cesguiro.controller.webModel.genre;

import es.cesguiro.domain.model.query.GenreQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GenreMapper {

    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    List<String> toGenreNameList(List<GenreQuery> genreQueries);

    default String toGenreName(GenreQuery genreQuery) {
        return genreQuery.getName();
    }
}
