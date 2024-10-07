package es.cesguiro.domain.repository;

import es.cesguiro.domain.model.command.GenreCommand;
import es.cesguiro.domain.model.query.GenreQuery;

import java.util.List;

public interface GenreRepository {

    List<GenreQuery> getByIsbnBook(String isbn);

    void insert(GenreCommand genreCommand);
}
