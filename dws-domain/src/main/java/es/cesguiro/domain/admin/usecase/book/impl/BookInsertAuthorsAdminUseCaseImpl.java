package es.cesguiro.domain.admin.usecase.book.impl;

import es.cesguiro.common.annotation.DomainTransactional;
import es.cesguiro.common.annotation.DomainUseCase;
import es.cesguiro.common.exception.ResourceNotFoundException;
import es.cesguiro.domain.admin.model.Author;
import es.cesguiro.domain.admin.model.Book;
import es.cesguiro.domain.admin.model.Genre;
import es.cesguiro.domain.admin.service.AuthorAdminService;
import es.cesguiro.domain.admin.service.BookAdminService;
import es.cesguiro.domain.admin.service.GenreAdminService;
import es.cesguiro.domain.admin.usecase.book.BookInsertAuthorsAdminUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainUseCase
@DomainTransactional
@RequiredArgsConstructor
public class BookInsertAuthorsAdminUseCaseImpl implements BookInsertAuthorsAdminUseCase {


    private final BookAdminService bookAdminService;
    private final AuthorAdminService authorAdminService;
    private final GenreAdminService genreAdminService;

    @Override
    public void execute(int id, List<Author> authors) {
        Book book = bookAdminService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book " + id + " not found"));
        List<Genre> genres = genreAdminService.getByIdBook(book.getId());
        book.setGenres(genres);
        List<Author> authorList = authorAdminService.getByIdBook(book.getId());
        book.setAuthors(authorList);
        authorAdminService
                .findAllById(authors)
                .forEach(author -> bookAdminService.addAuthor(book, author));
        bookAdminService.save(book);
    }
}
