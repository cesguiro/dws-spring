package es.cesguiro.domain.admin.usecase.book.impl;

import es.cesguiro.common.annotation.DomainUseCase;
import es.cesguiro.domain.admin.model.Book;
import es.cesguiro.domain.admin.service.BookAdminService;
import es.cesguiro.domain.admin.usecase.book.GetAllUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainUseCase
@RequiredArgsConstructor
public class GetAllUseCaseImpl implements GetAllUseCase {

    private final BookAdminService bookAdminService;

    @Override
    public List<Book> execute(int page, int size) {
        return bookAdminService.getAll(page, size);
    }
}
