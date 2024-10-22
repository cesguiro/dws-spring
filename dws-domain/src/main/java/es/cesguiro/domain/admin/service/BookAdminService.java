package es.cesguiro.domain.admin.service;

import es.cesguiro.domain.admin.model.Author;
import es.cesguiro.domain.admin.model.Book;
import es.cesguiro.domain.admin.model.Genre;

import java.util.List;
import java.util.Optional;

public interface BookAdminService {
    List<Book> getAll();

    List<Book> getAll(int page, int size);

    int count();

    Optional<Book> findByIsbn(String isbn);

    Optional<Book> findById(long id);

    void save(Book book);

    void addAuthor(Book book, Author author);

    void addGenre(Book book, Genre genre);
}
