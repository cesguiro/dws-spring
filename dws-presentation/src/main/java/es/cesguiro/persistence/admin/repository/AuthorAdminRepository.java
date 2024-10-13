package es.cesguiro.persistence.admin.repository;

import es.cesguiro.domain.admin.model.Author;

import java.util.List;

public interface AuthorAdminRepository {
    List<Author> getByIsbnBook(String isbn);
}
