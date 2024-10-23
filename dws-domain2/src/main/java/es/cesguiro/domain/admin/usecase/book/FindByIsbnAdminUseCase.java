package es.cesguiro.domain.admin.usecase.book;

import es.cesguiro.domain.admin.model.Book;

public interface FindByIsbnAdminUseCase {

    Book execute(String isbn);
}
