package com.fpmislata.persistence.repository;

import com.fpmislata.domain.model.Author;

import java.util.List;

public interface AuthorRepository {

    List<Author> getByIsbnBook(String isbn);
}
