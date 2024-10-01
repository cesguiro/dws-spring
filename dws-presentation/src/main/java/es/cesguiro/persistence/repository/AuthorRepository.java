package es.cesguiro.persistence.repository;

import es.cesguiro.domain.model.Author;

import java.util.List;

public interface AuthorRepository {

    List<Author> getByIsbnBook(String isbn);
}
