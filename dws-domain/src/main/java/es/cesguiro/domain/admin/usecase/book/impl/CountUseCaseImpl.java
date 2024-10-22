package es.cesguiro.domain.admin.usecase.book.impl;

import es.cesguiro.common.annotation.DomainUseCase;
import es.cesguiro.domain.admin.service.BookAdminService;
import es.cesguiro.domain.admin.usecase.book.CountUseCase;
import lombok.RequiredArgsConstructor;

@DomainUseCase
@RequiredArgsConstructor
public class CountUseCaseImpl implements CountUseCase {

    private final BookAdminService bookAdminService;

    @Override
    public int execute() {
        return bookAdminService.count();
    }
}
