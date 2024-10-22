package es.cesguiro.domain.admin.usecase;

import es.cesguiro.domain.admin.model.Book;

public interface FindBookByIsbnUseCase {

    Book execute(String isbn);
}
