package es.cesguiro.domain.repository;

import es.cesguiro.domain.model.command.GenreCommand;
import es.cesguiro.domain.model.query.GenreQuery;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    List<GenreQuery> getByIsbnBook(String isbn);

    void insert(GenreCommand genreCommand);

    Optional<GenreQuery> findBySlug(String slug);
}
