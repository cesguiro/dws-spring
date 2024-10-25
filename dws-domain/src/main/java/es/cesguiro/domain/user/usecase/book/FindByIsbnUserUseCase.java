package es.cesguiro.domain.user.usecase.book;

import es.cesguiro.domain.user.model.Book;

public interface FindByIsbnUserUseCase {
    Book execute(String isbn);
}
