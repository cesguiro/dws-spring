package es.cesguiro.domain.usecase.book;

import es.cesguiro.domain.model.Book;
import es.cesguiro.domain.model.ListWithCount;

public interface BookGetAllUseCase {

    ListWithCount<Book> execute(int page, int pageSize);
}
