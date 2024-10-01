package es.cesguiro.domain.service;

import es.cesguiro.domain.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();

    Book findByIsbn(String isbn);
}
