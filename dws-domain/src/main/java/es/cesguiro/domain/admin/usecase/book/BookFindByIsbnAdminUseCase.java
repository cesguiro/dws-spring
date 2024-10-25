package es.cesguiro.domain.admin.usecase.book;

import es.cesguiro.domain.admin.model.Book;

public interface BookFindByIsbnAdminUseCase {


    Book execute(String isbn);
}
