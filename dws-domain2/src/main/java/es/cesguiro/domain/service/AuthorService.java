package es.cesguiro.domain.service;

import es.cesguiro.domain.model.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getByIdBook(long idBook);

    List<Author> getByIsbnBook(String isbn);

    List<Author> findAllById(List<Author> authors);
}
