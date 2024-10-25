package es.cesguiro.controller.user.webmodel.genre;

import es.cesguiro.domain.usecase.book.user.model.GenreUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GenreMapper {

    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    List<String> toGenreNameList(List<GenreUser> genres);

    default String toGenreName(GenreUser genre) {
        return genre.getName();
    }
}
