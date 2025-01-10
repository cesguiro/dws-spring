package es.cesguiro.domain.usecase.book.admin;

import es.cesguiro.domain.model.Author;

import java.util.List;

public interface BookInsertAuthorsUseCase {
    void execute(Integer id, List<Author> authors);
}
