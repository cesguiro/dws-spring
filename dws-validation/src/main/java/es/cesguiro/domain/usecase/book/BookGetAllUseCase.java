package es.cesguiro.domain.usecase.book;

import es.cesguiro.domain.dto.output.BookCollectionDto;
import es.cesguiro.domain.model.Book;
import es.cesguiro.domain.model.ListWithCount;

public interface BookGetAllUseCase {

    ListWithCount<BookCollectionDto> execute(int page, int pageSize);
}
