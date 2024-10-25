package es.cesguiro.domain.user.usecase.book;

import es.cesguiro.domain.user.model.Book;

public interface BookFindByIsbnUserUseCase {
    Book execute(String isbn);
}
