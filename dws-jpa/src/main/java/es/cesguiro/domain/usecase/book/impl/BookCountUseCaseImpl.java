package es.cesguiro.domain.usecase.book.impl;

import es.cesguiro.common.annotation.DomainTransactional;
import es.cesguiro.common.annotation.DomainUseCase;
import es.cesguiro.domain.service.BookService;
import es.cesguiro.domain.usecase.book.BookCountUseCase;
import lombok.RequiredArgsConstructor;

@DomainUseCase
@DomainTransactional
@RequiredArgsConstructor
public class BookCountUseCaseImpl implements BookCountUseCase {

    private final BookService bookService;

    @Override
    public int execute() {
        return bookService.count();
    }
}
