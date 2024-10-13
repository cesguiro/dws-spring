package es.cesguiro.domain.user.repository;

import es.cesguiro.domain.user.model.command.GenreCommand;
import es.cesguiro.domain.user.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    List<Genre> getByIsbnBook(String isbn);

    Optional<Genre> findBySlug(String slug);

}
