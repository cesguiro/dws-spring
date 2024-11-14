package es.cesguiro.domain.admin.usecase.book.impl;

import es.cesguiro.common.annotation.DomainTransactional;
import es.cesguiro.common.annotation.DomainUseCase;
import es.cesguiro.common.exception.ResourceNotFoundException;
import es.cesguiro.domain.admin.model.Author;
import es.cesguiro.domain.admin.model.Book;
import es.cesguiro.domain.admin.model.Genre;
import es.cesguiro.domain.admin.repository.AuthorAdminRepository;
import es.cesguiro.domain.admin.service.AuthorAdminService;
import es.cesguiro.domain.admin.service.BookAdminService;
import es.cesguiro.domain.admin.service.GenreAdminService;
import es.cesguiro.domain.admin.usecase.book.BookInsertGenresAdminUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainUseCase
@DomainTransactional
@RequiredArgsConstructor
public class BookInsertGenresAdminUseCaseImpl implements BookInsertGenresAdminUseCase {

    private final BookAdminService bookAdminService;
    private final GenreAdminService genreAdminService;
    private final AuthorAdminService authorAdminService;

    @Override
    public void execute(int id, List<Genre> genres) {
        Book book = bookAdminService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book " + id + " not found"));
        List<Genre> genreList = genreAdminService.getByIdBook(book.getId());
        book.setGenres(genreList);
        List<Author> authorList = authorAdminService.getByIdBook(book.getId());
        book.setAuthors(authorList);
        genreAdminService
                .findAllById(genres)
                .forEach(genre -> bookAdminService.addGenre(book, genre));
        bookAdminService.save(book);
    }
}
