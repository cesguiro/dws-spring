package es.cesguiro.domain.usecase.book.user;

import es.cesguiro.domain.usecase.book.user.model.BookUser;

import java.util.List;

public interface BookGetAllUserUseCase {

    List<BookUser> execute(int page, int pageSize);

}
