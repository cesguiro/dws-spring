package es.cesguiro.domain.repository;

import es.cesguiro.domain.model.query.AuthorQuery;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    List<AuthorQuery> getByIsbnBook(String isbn);

    Optional<AuthorQuery> findById(long id);
}
