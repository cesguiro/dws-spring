package es.cesguiro.domain.repository;

import es.cesguiro.domain.model.Genre;

import java.util.List;

public interface GenreRepository {
    List<Genre> getByIsbnBook(String isbn);

    List<Genre> getByIdBook(long idBook);

    List<Genre> findAllById(Long[] ids);
}
