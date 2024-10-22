package es.cesguiro.domain.user.repository;

import es.cesguiro.domain.user.model.Genre;

import java.util.List;

public interface GenreUserRepository {

    List<Genre> getByIsbnBook(String isbn);
}