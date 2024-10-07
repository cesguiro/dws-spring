package es.cesguiro.domain.repository;

import es.cesguiro.domain.model.query.AuthorQuery;

import java.util.List;

public interface AuthorRepository {

    List<AuthorQuery> getByIsbnBook(String isbn);
}
