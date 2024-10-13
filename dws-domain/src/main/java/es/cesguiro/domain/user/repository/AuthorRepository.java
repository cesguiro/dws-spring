package es.cesguiro.domain.user.repository;

import es.cesguiro.domain.user.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    List<Author> getByIsbnBook(String isbn);

    Optional<Author> findById(long id);
}
