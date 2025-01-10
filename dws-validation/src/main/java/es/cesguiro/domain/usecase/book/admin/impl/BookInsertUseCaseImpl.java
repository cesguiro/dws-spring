package es.cesguiro.domain.usecase.book.admin.impl;

import es.cesguiro.common.annotation.DomainTransactional;
import es.cesguiro.common.annotation.DomainUseCase;
import es.cesguiro.common.exception.ResourceAlreadyExistsException;
import es.cesguiro.common.exception.ResourceNotFoundException;
import es.cesguiro.domain.dto.input.BookInsertDto;
import es.cesguiro.domain.model.Book;
import es.cesguiro.domain.service.*;
import es.cesguiro.domain.usecase.book.admin.BookInsertUseCase;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

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
    public void execute(BookInsertDto bookInsertDto) {
        bookService.findByIsbn(bookInsertDto.isbn())
                .ifPresent(book -> {
                    throw new ResourceAlreadyExistsException("Book with ISBN " + bookInsertDto.isbn() + " already exists");
                });

        Book book = new Book(
                bookInsertDto.isbn(),
                bookInsertDto.titleEs(),
                bookInsertDto.titleEn(),
                bookInsertDto.synopsisEs(),
                bookInsertDto.synopsisEn(),
                bookInsertDto.price(),
                bookInsertDto.discount(),
                bookInsertDto.cover()
        );
        book.setPublisher(publisherService
                .findById(bookInsertDto.publisherId())
                .orElseThrow(() -> new ResourceNotFoundException("Publisher " + book.getPublisher().getName() + " not found")));
        book.setCategory(categoryService
                .findById(bookInsertDto.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category " + book.getCategory().getId() + " not found")));
        book.setAuthors(authorService
                .findAllById(bookInsertDto.authorIds()));
        book.setGenres(genreService
                .findAllById(bookInsertDto.genreIds()));
        bookService.save(book);
    }
}
