package es.cesguiro.domain.usecase.book.impl;

import es.cesguiro.common.annotation.DomainTransactional;
import es.cesguiro.common.annotation.DomainUseCase;
import es.cesguiro.domain.model.Book;
import es.cesguiro.domain.model.ListWithCount;
import es.cesguiro.domain.service.BookService;
import es.cesguiro.domain.usecase.book.BookGetAllUseCase;
import lombok.RequiredArgsConstructor;

@DomainUseCase
@DomainTransactional
@RequiredArgsConstructor
public class BookGetAllUseCaseImpl implements BookGetAllUseCase {

    private final BookService bookService;

    @Override
    public ListWithCount<Book> execute(int page, int pageSize) {
        return bookService.getAll(page, pageSize);
    }
}
