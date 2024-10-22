package es.cesguiro.domain.admin.usecase.book.impl;

import es.cesguiro.common.annotation.DomainTransactional;
import es.cesguiro.common.annotation.DomainUseCase;
import es.cesguiro.common.exception.ResourceNotFoundException;
import es.cesguiro.domain.admin.model.Author;
import es.cesguiro.domain.admin.model.Book;
import es.cesguiro.domain.admin.service.AuthorAdminService;
import es.cesguiro.domain.admin.service.BookAdminService;
import es.cesguiro.domain.admin.usecase.book.InsertAuthorsUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainUseCase
@DomainTransactional
@RequiredArgsConstructor
public class InsertAuthorsUseCaseImpl implements InsertAuthorsUseCase {

    private final BookAdminService bookAdminService;
    private final AuthorAdminService authorAdminService;

    @Override
    public void execute(int id, List<Author> authors) {
        Book book = bookAdminService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book " + id + " not found"));
        authorAdminService
                .findAllById(authors)
                .forEach(author -> bookAdminService.addAuthor(book, author));
        bookAdminService.save(book);
    }
}
