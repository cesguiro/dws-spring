package es.cesguiro.domain.admin.usecase.book.impl;

import es.cesguiro.common.annotation.DomainUseCase;
import es.cesguiro.common.exception.ResourceNotFoundException;
import es.cesguiro.domain.admin.model.Author;
import es.cesguiro.domain.admin.model.Book;
import es.cesguiro.domain.admin.model.Genre;
import es.cesguiro.domain.admin.service.AuthorAdminService;
import es.cesguiro.domain.admin.service.BookAdminService;
import es.cesguiro.domain.admin.service.GenreAdminService;
import es.cesguiro.domain.admin.usecase.book.BookFindByIsbnAdminUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainUseCase
@RequiredArgsConstructor
public class BookFindByIsbnAdminUseCaseImpl implements BookFindByIsbnAdminUseCase {


    private final BookAdminService bookAdminService;
    private final GenreAdminService genreAdminService;
    private final AuthorAdminService authorAdminService;

    @Override
    public Book execute(String isbn) {
        Book book = bookAdminService.findByIsbn(isbn).orElseThrow(() -> new ResourceNotFoundException("Book isbn " + isbn + " not found"));
        List<Genre> genres = genreAdminService.getByIdBook(book.getId());
        book.setGenres(genres);
        List<Author> authors = authorAdminService.getByIdBook(book.getId());
        book.setAuthors(authors);
        return book;
    }
}
