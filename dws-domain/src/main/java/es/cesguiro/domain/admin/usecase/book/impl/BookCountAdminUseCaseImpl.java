package es.cesguiro.domain.admin.usecase.book.impl;

import es.cesguiro.common.annotation.DomainUseCase;
import es.cesguiro.domain.admin.service.BookAdminService;
import es.cesguiro.domain.admin.usecase.book.BookCountAdminUseCase;

import lombok.RequiredArgsConstructor;

@DomainUseCase
@RequiredArgsConstructor
public class BookCountAdminUseCaseImpl implements BookCountAdminUseCase {


    private final BookAdminService bookAdminService;

    @Override
    public int execute() {
        return bookAdminService.count();
    }
}
