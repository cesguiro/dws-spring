package es.cesguiro.persistence.admin.repository;

import es.cesguiro.domain.admin.model.Genre;

import java.util.List;

public interface GenreAdminRepository {
    List<Genre> getByIsbnBook(String isbn);
}
