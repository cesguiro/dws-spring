package es.cesguiro.domain.usecase.book.admin.impl;

import es.cesguiro.common.annotation.DomainUseCase;
import es.cesguiro.common.exception.ResourceNotFoundException;
import es.cesguiro.domain.model.Author;
import es.cesguiro.domain.model.Book;
import es.cesguiro.domain.model.Genre;
import es.cesguiro.domain.service.AuthorService;
import es.cesguiro.domain.service.BookService;
import es.cesguiro.domain.service.GenreService;
import es.cesguiro.domain.usecase.book.admin.BookFindByIsbnAdminUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainUseCase
@RequiredArgsConstructor
public class BookFindByIsbnAdminUseCaseImpl implements BookFindByIsbnAdminUseCase {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    public Book execute(String isbn) {
        Book book = bookService
                .findByIsbn(isbn)
                .orElseThrow(() -> new ResourceNotFoundException("Book isbn " + isbn + " not found"));
        List<Author> authors = authorService
                .getByIsbnBook(book.getIsbn());
        book.setAuthors(authors);
        List<Genre> genres = genreService
                .getByIsbnBook(book.getIsbn());
        book.setGenres(genres);
        return book;
    }
}
