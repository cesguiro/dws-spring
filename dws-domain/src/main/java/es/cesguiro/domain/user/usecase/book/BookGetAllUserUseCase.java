package es.cesguiro.domain.user.usecase.book;

import es.cesguiro.domain.user.model.Book;

import java.util.List;

public interface BookGetAllUserUseCase {
    List<Book> execute(int i, int pageSize);
}
