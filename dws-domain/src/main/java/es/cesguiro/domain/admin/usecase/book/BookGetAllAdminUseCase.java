package es.cesguiro.domain.admin.usecase.book;

import es.cesguiro.domain.admin.model.Book;

import java.util.List;


public interface BookGetAllAdminUseCase {

    List<Book> execute(int page, int size);
}
