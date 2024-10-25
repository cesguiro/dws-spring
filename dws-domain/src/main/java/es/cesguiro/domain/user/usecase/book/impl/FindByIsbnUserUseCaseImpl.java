package es.cesguiro.domain.user.usecase.book.impl;

import es.cesguiro.common.annotation.DomainTransactional;
import es.cesguiro.common.annotation.DomainUseCase;
import es.cesguiro.common.exception.ResourceNotFoundException;
import es.cesguiro.domain.user.model.Book;
import es.cesguiro.domain.user.service.BookUserService;
import es.cesguiro.domain.user.usecase.book.FindByIsbnUserUseCase;
import lombok.RequiredArgsConstructor;

@DomainUseCase
@DomainTransactional
@RequiredArgsConstructor
public class FindByIsbnUserUseCaseImpl implements FindByIsbnUserUseCase {

    private final BookUserService bookUserService;

    @Override
    public Book execute(String isbn) {
        return bookUserService.findByIsbn(isbn).orElseThrow(() -> new ResourceNotFoundException("Book isbn " + isbn + " not found"));
    }
}
