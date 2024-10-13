package es.cesguiro.domain.user.repository;

import es.cesguiro.domain.user.model.command.BookCommand;
import es.cesguiro.domain.user.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> getAll();

    Optional<Book> findByIsbn(String isbn);

    void addGenre(String bookIsbn, long genreId);

    void addAuthor(String bookIsbn, long authorId);

    Optional<BookCommand> insert(BookCommand bookCommand);
}
