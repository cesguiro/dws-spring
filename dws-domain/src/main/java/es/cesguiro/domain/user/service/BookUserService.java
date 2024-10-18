package es.cesguiro.domain.user.service;

import es.cesguiro.domain.user.model.Book;

import java.util.List;

public interface BookUserService {
    List<Book> getAll();

    Book findByIsbn(String isbn);

    List<Book> getAll(int page, int size);

    int count();
}
