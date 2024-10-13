package es.cesguiro.persistence.user.repository;

import es.cesguiro.domain.user.model.Author;

import java.util.List;

public interface AuthorUserRepository {

    List<Author> getByIsbnBook(String isbn);
}
