package es.cesguiro.domain.repository;

import es.cesguiro.domain.model.query.BookQuery;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<BookQuery> getAll();

    Optional<BookQuery> findByIsbn(String isbn);
}
