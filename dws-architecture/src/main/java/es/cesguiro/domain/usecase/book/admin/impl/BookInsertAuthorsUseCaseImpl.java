package es.cesguiro.domain.usecase.book.admin.impl;

import es.cesguiro.common.annotation.DomainTransactional;
import es.cesguiro.common.annotation.DomainUseCase;
import es.cesguiro.common.exception.ResourceNotFoundException;
import es.cesguiro.domain.model.Author;
import es.cesguiro.domain.model.Book;
import es.cesguiro.domain.service.AuthorService;
import es.cesguiro.domain.service.BookService;
import es.cesguiro.domain.usecase.book.admin.BookInsertAuthorsUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainUseCase
@DomainTransactional
@RequiredArgsConstructor
public class BookInsertAuthorsUseCaseImpl implements BookInsertAuthorsUseCase {

    private final BookService bookService;
    private final AuthorService authorService;

    @Override
    public void execute(Integer id, List<Author> authors) {
        Book book = bookService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book " + id + " not found"));
        authorService
                .findAllById(authors)
                .forEach(author -> bookService.addAuthor(book, author));
        bookService.save(book);
    }
}
