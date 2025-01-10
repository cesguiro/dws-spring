package es.cesguiro.domain.port.input;

import es.cesguiro.domain.port.output.dto.BookCollectionDto;
import es.cesguiro.domain.model.ListWithCount;

public interface BookGetAllUseCase {

    ListWithCount<BookCollectionDto> execute(int page, int pageSize);
}
