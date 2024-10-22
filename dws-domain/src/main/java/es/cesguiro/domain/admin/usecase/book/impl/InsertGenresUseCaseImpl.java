package es.cesguiro.domain.admin.usecase.book.impl;

import es.cesguiro.common.annotation.DomainTransactional;
import es.cesguiro.common.annotation.DomainUseCase;
import es.cesguiro.common.exception.ResourceNotFoundException;
import es.cesguiro.domain.admin.model.Book;
import es.cesguiro.domain.admin.model.Genre;
import es.cesguiro.domain.admin.service.BookAdminService;
import es.cesguiro.domain.admin.service.GenreAdminService;
import es.cesguiro.domain.admin.usecase.book.InsertGenresUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainUseCase
@DomainTransactional
@RequiredArgsConstructor
public class InsertGenresUseCaseImpl implements InsertGenresUseCase {

    private final BookAdminService bookAdminService;
    private final GenreAdminService genreAdminService;

    @Override
    public void execute(int id, List<Genre> genres) {
        Book book = bookAdminService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book " + id + " not found"));
        genreAdminService
                .findAllById(genres)
                .forEach(genre -> bookAdminService.addGenre(book, genre));
        bookAdminService.save(book);
    }
}
