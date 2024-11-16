package es.cesguiro.domain.usecase.book.impl;

import es.cesguiro.common.annotation.DomainUseCase;
import es.cesguiro.common.exception.ResourceNotFoundException;
import es.cesguiro.domain.model.Author;
import es.cesguiro.domain.model.Book;
import es.cesguiro.domain.service.AuthorService;
import es.cesguiro.domain.service.BookService;
import es.cesguiro.domain.service.CategoryService;
import es.cesguiro.domain.service.GenreService;
import es.cesguiro.domain.usecase.book.BookFindByIsbnUseCase;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@DomainUseCase
@RequiredArgsConstructor
public class BookFindByIsbnUseCaseImpl implements BookFindByIsbnUseCase {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    public Book execute(String isbn) {
        Book book = bookService
                .findByIsbn(isbn)
                .orElseThrow(() -> new ResourceNotFoundException("Book isbn " + isbn + " not found"));
        book.setAuthors(authorService.getByIdBook(book.getId()));
        book.setGenres(genreService.getByIdBook(book.getId()));
        return book;
    }
}
