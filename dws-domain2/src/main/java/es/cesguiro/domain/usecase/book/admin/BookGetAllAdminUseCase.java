package es.cesguiro.domain.usecase.book.admin;

import es.cesguiro.domain.model.Book;

import java.util.List;

public interface BookGetAllAdminUseCase {

    List<Book> execute(int page, int pageSize);
}
