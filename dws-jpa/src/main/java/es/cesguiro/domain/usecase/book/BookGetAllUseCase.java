package es.cesguiro.domain.usecase.book;

import es.cesguiro.common.PaginatedResponse;
import es.cesguiro.domain.model.Book;

public interface BookGetAllUseCase {

    PaginatedResponse<Book> execute(int page, int pageSize);
}
