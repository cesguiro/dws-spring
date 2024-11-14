package es.cesguiro.persistence.dao.db;

import es.cesguiro.domain.model.Author;
import es.cesguiro.domain.model.Book;
import es.cesguiro.domain.model.Genre;

import java.util.List;
import java.util.Optional;

public interface BookDaoDb extends GenericDaoDb<Book> {

    Optional<Book> findByIsbn(String isbn);
    void deleteAuthors(long id);
    void insertAuthors(long id, List<Author> authors);
    void deleteGenres(long id);
    void insertGenres(long id, List<Genre> genres);

}
