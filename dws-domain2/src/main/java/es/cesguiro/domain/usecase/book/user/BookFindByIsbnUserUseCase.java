package es.cesguiro.domain.usecase.book.user;

import es.cesguiro.domain.usecase.book.user.model.BookUser;

public interface BookFindByIsbnUserUseCase {
    BookUser execute(String isbn);
}
