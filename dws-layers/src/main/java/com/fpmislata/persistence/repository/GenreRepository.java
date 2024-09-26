package com.fpmislata.persistence.repository;

import com.fpmislata.domain.model.Genre;

import java.util.List;

public interface GenreRepository {

    List<Genre> getByIsbnBook(String isbn);
}
