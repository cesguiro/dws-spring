package es.cesguiro.domain.repository;

import es.cesguiro.domain.model.Book;
import es.cesguiro.domain.model.ListWithCount;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<Book> getAll();

    ListWithCount<Book> getAll(int page, int size);

    long count();

    Optional<Book> findByIsbn(String isbn);

    Optional<Book> findById(long id);

    void save(Book book);


    void delete(long id);
}
