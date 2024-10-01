package es.cesguiro.persistence.repository;

import es.cesguiro.domain.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> getAll();

    Optional<Book> findByIsbn(String isbn);
}
