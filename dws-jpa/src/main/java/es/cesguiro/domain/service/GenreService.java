package es.cesguiro.domain.service;

import es.cesguiro.domain.model.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> getByIdBook(long idBook);

    List<Genre> findAllById(List<Genre> genres);
}
