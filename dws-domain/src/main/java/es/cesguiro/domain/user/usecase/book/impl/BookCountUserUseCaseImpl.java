package es.cesguiro.domain.user.usecase.book.impl;

import es.cesguiro.common.annotation.DomainUseCase;
import es.cesguiro.domain.user.service.BookUserService;
import es.cesguiro.domain.user.usecase.book.BookCountUserUseCase;
import lombok.RequiredArgsConstructor;

@DomainUseCase
@RequiredArgsConstructor
public class BookCountUserUseCaseImpl implements BookCountUserUseCase {

    private final BookUserService bookUserService;

    @Override
    public int execute() {
        return bookUserService.count();
    }
}
