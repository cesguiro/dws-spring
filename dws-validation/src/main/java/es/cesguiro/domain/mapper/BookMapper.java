package es.cesguiro.domain.mapper;

import es.cesguiro.domain.dto.output.BookCollectionDto;
import es.cesguiro.domain.model.Book;

public class BookMapper {

    public static BookCollectionDto toBookCollectionDto(Book book) {
        if (book == null) {
            return null;
        }
        return new BookCollectionDto(
                book.getIsbn(),
                book.getTitle(),
                book.getPrice(),
                book.getDiscount(),
                book.getCover()
        );
    }
}
