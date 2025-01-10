package es.cesguiro.domain.usecase.book.impl;

import es.cesguiro.common.annotation.DomainTransactional;
import es.cesguiro.common.annotation.DomainUseCase;
import es.cesguiro.domain.dto.output.BookCollectionDto;
import es.cesguiro.domain.mapper.BookMapper;
import es.cesguiro.domain.model.Book;
import es.cesguiro.domain.model.ListWithCount;
import es.cesguiro.domain.service.BookService;
import es.cesguiro.domain.usecase.book.BookGetAllUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainUseCase
@DomainTransactional
@RequiredArgsConstructor
public class BookGetAllUseCaseImpl implements BookGetAllUseCase {

    private final BookService bookService;

    @Override
    public ListWithCount<BookCollectionDto> execute(int page, int pageSize) {
        ListWithCount<Book> all = bookService.getAll(page, pageSize);
        List<BookCollectionDto> data = all
                .getList()
                .stream()
                .map(BookMapper::toBookCollectionDto)
                .toList();
        return new ListWithCount<>(data, all.getCount());
    }
}
