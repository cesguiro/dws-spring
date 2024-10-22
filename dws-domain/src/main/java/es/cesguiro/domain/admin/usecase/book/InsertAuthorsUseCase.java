package es.cesguiro.domain.admin.usecase.book;

import es.cesguiro.domain.admin.model.Author;

import java.util.List;

public interface InsertAuthorsUseCase {

    void execute(int id, List<Author> authors);
}
