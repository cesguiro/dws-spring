package es.cesguiro.domain.usecase.book.admin.impl;

import es.cesguiro.common.annotation.DomainTransactional;
import es.cesguiro.common.annotation.DomainUseCase;
import es.cesguiro.common.exception.ResourceNotFoundException;
import es.cesguiro.domain.service.BookService;
import es.cesguiro.domain.usecase.book.admin.BookDeleteUseCase;
import lombok.RequiredArgsConstructor;

@DomainUseCase
@DomainTransactional
@RequiredArgsConstructor
public class BookDeleteUseCaseImpl implements BookDeleteUseCase {

    private final BookService bookService;

    @Override
    public void execute(long id) {
        bookService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        bookService.delete(id);
    }
}
