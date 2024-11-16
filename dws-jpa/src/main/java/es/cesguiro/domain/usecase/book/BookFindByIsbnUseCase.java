package es.cesguiro.domain.usecase.book;

import es.cesguiro.domain.model.Book;

public interface BookFindByIsbnUseCase {
    Book execute(String isbn);
}
