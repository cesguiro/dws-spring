package es.cesguiro.domain.admin.usecase.book;

import es.cesguiro.domain.admin.model.Author;

import java.util.List;

public interface InsertAuthorsAdminUseCase {

    void execute(int id, List<Author> authors);
}
