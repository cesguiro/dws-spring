package es.cesguiro.domain.admin.service;

import es.cesguiro.domain.admin.model.Genre;

import java.util.List;

public interface GenreAdminService {

    List<Genre> getByIdBook(long idBook);

    List<Genre> findAllById(List<Genre> genres);
}
