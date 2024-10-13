package es.cesguiro.persistence.admin.repository;

import es.cesguiro.domain.admin.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookAdminRepository {
    List<Book> getAll();

    List<Book> getAll(int page, int size);

    int count();

    Optional<Book> findByIsbn(String isbn);
}
