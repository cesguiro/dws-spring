package es.cesguiro.domain.usecase.book.user.impl;

import es.cesguiro.common.annotation.DomainUseCase;
import es.cesguiro.common.exception.ResourceNotFoundException;
import es.cesguiro.domain.service.AuthorService;
import es.cesguiro.domain.service.BookService;
import es.cesguiro.domain.service.GenreService;
import es.cesguiro.domain.usecase.book.user.BookFindByIsbnUserUseCase;
import es.cesguiro.domain.usecase.book.user.mapper.AuthorMapper;
import es.cesguiro.domain.usecase.book.user.mapper.BookMapper;
import es.cesguiro.domain.usecase.book.user.mapper.GenreMapper;
import es.cesguiro.domain.usecase.book.user.model.AuthorUser;
import es.cesguiro.domain.usecase.book.user.model.BookUser;
import es.cesguiro.domain.usecase.book.user.model.GenreUser;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainUseCase
@RequiredArgsConstructor
public class BookFindByIsbnUserUseCaseImpl implements BookFindByIsbnUserUseCase {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    public BookUser execute(String isbn) {
        BookUser bookUser = BookMapper.INSTANCE.toBookUser(
                bookService
                        .findByIsbn(isbn)
                        .orElseThrow(() -> new ResourceNotFoundException("Book isbn " + isbn + " not found"))
        );
        List<AuthorUser> authorUsers = authorService
                .getByIsbnBook(bookUser.getIsbn())
                .stream()
                .map(AuthorMapper.INSTANCE::toAuthorUser)
                .toList();
        bookUser.setAuthors(authorUsers);
        List<GenreUser> genreUsers = genreService
                .getByIsbnBook(bookUser.getIsbn())
                .stream()
                .map(GenreMapper.INSTANCE::toGenreUser)
                .toList();
        bookUser.setGenres(genreUsers);
        return bookUser;
    }
}
