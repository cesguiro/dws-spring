package es.cesguiro.domain.service;

import es.cesguiro.domain.model.command.BookCommand;
import es.cesguiro.domain.model.query.BookQuery;

import java.util.List;

public interface BookService {
    List<BookQuery> getAll();

    BookQuery findByIsbn(String isbn);

    void addGenre(String isbn, String genreSlug);

    void addAuthor(String isbn, long authorId);

    void insert(BookCommand bookCommand);
}
