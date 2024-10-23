package es.cesguiro.domain.user.usecase.book.impl;

import es.cesguiro.common.annotation.DomainTransactional;
import es.cesguiro.common.annotation.DomainUseCase;
import es.cesguiro.domain.user.model.Book;
import es.cesguiro.domain.user.service.BookUserService;
import es.cesguiro.domain.user.usecase.book.GetAllUserUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainUseCase
@DomainTransactional
@RequiredArgsConstructor
public class GetAllUserUseCaseImpl implements GetAllUserUseCase {

    private final BookUserService bookUserService;

    @Override
    public List<Book> execute(int i, int pageSize) {
        return bookUserService.getAll(i, pageSize);
    }
}
