package es.cesguiro.domain.user.model.mapper;

import es.cesguiro.domain.user.model.command.BookCommand;
import es.cesguiro.domain.user.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "titleEs", source = "title")
    @Mapping(target = "titleEn", source = "title")
    @Mapping(target = "synopsisEs", source = "synopsis")
    @Mapping(target = "synopsisEn", source = "synopsis")
    BookCommand toBookCommand(Book book);

}
