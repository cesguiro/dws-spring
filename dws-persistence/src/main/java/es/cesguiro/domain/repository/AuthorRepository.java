package es.cesguiro.domain.repository;

import es.cesguiro.domain.model.Author;

import java.util.List;

public interface AuthorRepository {
    List<Author> getByIsbnBook(String isbn);

    List<Author> getByIdBook(long idBook);

    List<Author> findAllById(Long[] ids);
}
