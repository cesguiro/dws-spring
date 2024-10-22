package es.cesguiro.domain.admin.usecase.impl;

import es.cesguiro.common.annotation.DomainUseCase;
import es.cesguiro.common.exception.ResourceNotFoundException;
import es.cesguiro.domain.admin.model.Book;
import es.cesguiro.domain.admin.service.BookAdminService;
import es.cesguiro.domain.admin.usecase.FindBookByIsbnUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@DomainUseCase
@RequiredArgsConstructor
public class FindByIsbnUseCaseImpl implements FindBookByIsbnUseCase {

    private final BookAdminService bookAdminService;

    @Override
    public Book execute(String isbn) {
        return bookAdminService.findByIsbn(isbn).orElseThrow(() -> new ResourceNotFoundException("Book isbn " + isbn + " not found"));
    }
}
