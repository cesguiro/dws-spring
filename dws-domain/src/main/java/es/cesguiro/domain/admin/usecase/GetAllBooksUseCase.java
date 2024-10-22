package es.cesguiro.domain.admin.usecase;

import es.cesguiro.domain.admin.model.Book;

import java.util.List;

public interface GetAllBooksUseCase {

    List<Book> execute(int page, int size);
}
