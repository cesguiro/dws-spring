package es.cesguiro.domain.admin.usecase.book.impl;

import es.cesguiro.common.annotation.DomainTransactional;
import es.cesguiro.common.annotation.DomainUseCase;
import es.cesguiro.common.exception.ResourceAlreadyExistsException;
import es.cesguiro.common.exception.ResourceNotFoundException;
import es.cesguiro.domain.admin.model.Book;
import es.cesguiro.domain.admin.service.*;
import es.cesguiro.domain.admin.usecase.book.BookInsertAdminUseCase;
import lombok.RequiredArgsConstructor;

@DomainUseCase
@DomainTransactional
@RequiredArgsConstructor
public class BookInsertAdminUseCaseImpl implements BookInsertAdminUseCase {

    private final BookAdminService bookAdminService;
    private final AuthorAdminService authorAdminService;
    private final GenreAdminService genreAdminService;
    private final PublisherAdminService publisherAdminService;
    private final CategoryAdminService categoryAdminService;

    @Override
    public void execute(Book book) {
        if(bookAdminService.findByIsbn(book.getIsbn()).isPresent()) {
            throw new ResourceAlreadyExistsException("Book with ISBN " + book.getIsbn() + " already exists");
        }
        book.setPublisher(publisherAdminService
                .findById(book.getPublisher().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Publisher " + book.getPublisher().getName() + " not found")));
        book.setCategory(categoryAdminService
                .findById(book.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category " + book.getCategory().getId() + " not found")));
        book.setAuthors(authorAdminService
                .findAllById(book.getAuthors()));
        book.setGenres(genreAdminService
                .findAllById(book.getGenres()));
        bookAdminService.save(book);
    }
}
