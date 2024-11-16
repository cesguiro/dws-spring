package es.cesguiro.domain.usecase.book;

import es.cesguiro.domain.model.Book;

import java.util.List;

public interface BookGetAllUseCase {

    List<Book> execute(int page, int pageSize);
}
