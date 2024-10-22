package es.cesguiro.domain.admin.repository;

import es.cesguiro.domain.admin.model.Author;

import java.util.List;

public interface AuthorAdminRepository {
    List<Author> getByIsbnBook(String isbn);

    List<Author> getByIdBook(long idBook);

    List<Author> findAllById(Long[] ids);
}
