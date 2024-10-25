package es.cesguiro.domain.usecase.book.admin;

import es.cesguiro.domain.model.Book;

public interface BookFindByIsbnAdminUseCase {
    Book execute(String isbn);
}
