package es.cesguiro.domain.admin.service;

import es.cesguiro.domain.admin.model.Author;

import java.util.List;

public interface AuthorAdminService {

    List<Author> getByIdBook(long idBook);

    List<Author> findAllById(List<Author> authors);
}
