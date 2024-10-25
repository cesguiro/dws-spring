package es.cesguiro.domain.usecase.book.admin.impl;

import es.cesguiro.common.annotation.DomainTransactional;
import es.cesguiro.common.annotation.DomainUseCase;
import es.cesguiro.common.exception.ResourceNotFoundException;
import es.cesguiro.domain.model.Book;
import es.cesguiro.domain.model.Genre;
import es.cesguiro.domain.service.BookService;
import es.cesguiro.domain.service.GenreService;
import es.cesguiro.domain.usecase.book.admin.BookInsertGenresUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainUseCase
@DomainTransactional
@RequiredArgsConstructor
public class BookInsertGenresUseCaseImpl implements BookInsertGenresUseCase {

    private final BookService bookService;
    private final GenreService genreService;

    @Override
    public void execute(Integer id, List<Genre> genres) {
        Book book = bookService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book " + id + " not found"));
        genreService
                .findAllById(genres)
                .forEach(genre -> bookService.addGenre(book, genre));
        bookService.save(book);
    }
}
