package es.cesguiro.persistence.repository;

import es.cesguiro.domain.model.Genre;

import java.util.List;

public interface GenreRepository {

    List<Genre> getByIsbnBook(String isbn);
}
