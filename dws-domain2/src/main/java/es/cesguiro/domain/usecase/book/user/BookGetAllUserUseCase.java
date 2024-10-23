package es.cesguiro.domain.usecase.book.user;

import es.cesguiro.domain.user.model.Book;

import java.util.List;

public interface BookGetAllUserUseCase {

    List<Book> execute(int page, int pageSize);

}
