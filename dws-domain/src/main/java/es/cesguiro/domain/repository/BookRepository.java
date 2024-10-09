package es.cesguiro.domain.repository;

import es.cesguiro.domain.model.command.BookCommand;
import es.cesguiro.domain.model.query.BookQuery;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<BookQuery> getAll();

    Optional<BookQuery> findByIsbn(String isbn);

    void addGenre(String isbn, long id);

    void addAuthor(String isbn, long authorId);

    Optional<BookQuery> insert(BookCommand bookCommand);
}
