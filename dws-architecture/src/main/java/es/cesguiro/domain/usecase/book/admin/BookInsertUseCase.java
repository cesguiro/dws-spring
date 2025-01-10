package es.cesguiro.domain.usecase.book.admin;

import es.cesguiro.domain.dto.input.BookInsertDto;

public interface BookInsertUseCase {
     void execute(BookInsertDto bookInsertDto);
}
