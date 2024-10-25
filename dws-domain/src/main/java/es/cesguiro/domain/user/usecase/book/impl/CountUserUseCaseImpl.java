package es.cesguiro.domain.user.usecase.book.impl;

import es.cesguiro.common.annotation.DomainTransactional;
import es.cesguiro.common.annotation.DomainUseCase;
import es.cesguiro.domain.user.service.BookUserService;
import es.cesguiro.domain.user.usecase.book.CountUserUseCase;
import lombok.RequiredArgsConstructor;

@DomainUseCase
@DomainTransactional
@RequiredArgsConstructor
public class CountUserUseCaseImpl implements CountUserUseCase {

    private final BookUserService bookUserService;

    @Override
    public int execute() {
        return bookUserService.count();
    }
}
