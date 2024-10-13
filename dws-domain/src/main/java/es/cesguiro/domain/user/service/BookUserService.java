package es.cesguiro.domain.user.service;

import es.cesguiro.domain.user.model.command.BookCommand;
import es.cesguiro.domain.user.model.Book;

import java.util.List;

public interface BookUserService {
    List<Book> getAll();

    Book findByIsbn(String isbn);

    void addGenre(String isbn, String genreSlug);

    void addAuthor(String isbn, long authorId);

    void insert(BookCommand bookCommand);
}
