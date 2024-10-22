package es.cesguiro.domain.admin.usecase;

import es.cesguiro.domain.admin.model.Book;

public interface InsertBookUseCase {

    void execute(Book book);
}
