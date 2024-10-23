package es.cesguiro.domain.user.usecase.book;

import es.cesguiro.domain.user.model.Book;

import java.util.List;

public interface GetAllUserUseCase {
    List<Book> execute(int i, int pageSize);
}