package es.cesguiro.domain.admin.repository;

import es.cesguiro.domain.admin.model.Genre;

import java.util.List;

public interface GenreAdminRepository {
    List<Genre> getByIsbnBook(String isbn);

    List<Genre> getByIdBook(int idBook);

    List<Genre> findAllById(Long[] ids);
}
