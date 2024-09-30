package es.cesguiro.controller.model.book;

import es.cesguiro.domain.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import es.cesguiro.controller.model.publisher.PublisherMapper;

@Mapper(uses = {PublisherMapper.class})
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookCollection toBookCollection(Book book);

    @Mapping(target = "publisherCollection", source ="")
    @Mapping(target = "authorCollection", ignore = true)
    BookDetail toBookDetail(Book book);
}
