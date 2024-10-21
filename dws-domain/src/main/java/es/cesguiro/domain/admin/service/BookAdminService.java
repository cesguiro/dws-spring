package es.cesguiro.domain.admin.service;

import es.cesguiro.domain.admin.model.Author;
import es.cesguiro.domain.admin.model.Book;
import es.cesguiro.domain.admin.model.Genre;

import java.util.List;

public interface BookAdminService {
    List<Book> getAll();

    List<Book> getAll(int page, int size);

    int count();

    Book findByIsbn(String isbn);

    void insertAuthors(int idBook, List<Author> authors);

    void insert(Book book);

    void insertGenres(Integer idBook, List<Genre> genres);
}
