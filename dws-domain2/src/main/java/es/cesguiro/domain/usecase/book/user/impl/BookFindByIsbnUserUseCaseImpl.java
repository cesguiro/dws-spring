package es.cesguiro.domain.usecase.book.user.impl;

import es.cesguiro.common.annotation.DomainUseCase;
import es.cesguiro.common.exception.ResourceNotFoundException;
import es.cesguiro.domain.service.BookService;
import es.cesguiro.domain.usecase.book.user.BookFindByIsbnUserUseCase;
import es.cesguiro.domain.usecase.book.user.mapper.BookMapper;
import es.cesguiro.domain.usecase.book.user.model.BookUser;
import lombok.RequiredArgsConstructor;

@DomainUseCase
@RequiredArgsConstructor
public class BookFindByIsbnUserUseCaseImpl implements BookFindByIsbnUserUseCase {

    private final BookService bookService;

    @Override
    public BookUser execute(String isbn) {
        return BookMapper.INSTANCE.toBookUser(
                bookService
                        .findByIsbn(isbn)
                        .orElseThrow(() -> new ResourceNotFoundException("Book isbn " + isbn + " not found"))
        );
    }
}
