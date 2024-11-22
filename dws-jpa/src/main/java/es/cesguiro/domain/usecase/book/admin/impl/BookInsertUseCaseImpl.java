package es.cesguiro.domain.usecase.book.admin.impl;

import es.cesguiro.common.annotation.DomainTransactional;
import es.cesguiro.common.annotation.DomainUseCase;
import es.cesguiro.common.exception.ResourceAlreadyExistsException;
import es.cesguiro.common.exception.ResourceNotFoundException;
import es.cesguiro.domain.model.Book;
import es.cesguiro.domain.service.*;
import es.cesguiro.domain.usecase.book.admin.BookInsertUseCase;
import lombok.RequiredArgsConstructor;

@DomainUseCase
@DomainTransactional
@RequiredArgsConstructor
public class BookInsertUseCaseImpl implements BookInsertUseCase {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final PublisherService publisherService;
    private final CategoryService categoryService;

    @Override
    public void execute(Book book) {
        if(bookService.findByIsbn(book.getIsbn()).isPresent()) {
            throw new ResourceAlreadyExistsException("Book with ISBN " + book.getIsbn() + " already exists");
        }
        book.setPublisher(publisherService
                .findById(book.getPublisher().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Publisher " + book.getPublisher().getName() + " not found")));
        book.setCategory(categoryService
                .findById(book.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category " + book.getCategory().getId() + " not found")));
        book.setAuthors(authorService
                .findAllById(book.getAuthors()));
        book.setGenres(genreService
                .findAllById(book.getGenres()));
        bookService.save(book);
    }
}
