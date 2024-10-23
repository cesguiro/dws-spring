package es.cesguiro.domain.usecase.book.user.impl;

import es.cesguiro.common.annotation.DomainTransactional;
import es.cesguiro.common.annotation.DomainUseCase;
import es.cesguiro.domain.repository.BookRepository;
import es.cesguiro.domain.service.BookService;
import es.cesguiro.domain.usecase.book.user.BookGetAllUserUseCase;
import es.cesguiro.domain.user.model.Book;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainUseCase
@DomainTransactional
@RequiredArgsConstructor
public class BookGetAllUserUseCaseImpl implements BookGetAllUserUseCase {

    private final BookService bookService;
    private final BookRepository bookRepository;

    @Override
    public List<Book> execute(int page, int pageSize) {
        return bookRepository.getAll();
    }
}