package es.cesguiro.controller.admin.webmodel.book;

import es.cesguiro.domain.port.output.dto.BookCollectionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "title", source = "bookCollectionDto.title")
    BookCollection toBookCollection(BookCollectionDto bookCollectionDto);

}
