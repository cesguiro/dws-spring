package es.cesguiro.domain.admin.service;

import es.cesguiro.domain.admin.model.Book;

import java.util.List;

public interface BookAdminService {
    List<Book> getAll();

    List<Book> getAll(int page, int size);

    int count();

    Book findByIsbn(String isbn);
}
