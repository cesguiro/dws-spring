package es.cesguiro.persistence.user.repository;

import es.cesguiro.domain.user.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookUserRepository {
    List<Book> getAll();

    Optional<Book> findByIsbn(String isbn);

    List<Book> getAll(int page, int size);

    int count();
}
