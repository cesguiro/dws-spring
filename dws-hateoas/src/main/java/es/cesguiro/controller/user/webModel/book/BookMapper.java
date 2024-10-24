package es.cesguiro.controller.user.webModel.book;

import es.cesguiro.controller.user.webModel.author.AuthorMapper;
import es.cesguiro.controller.user.webModel.publisher.PublisherMapper;
import es.cesguiro.domain.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PublisherMapper.class, AuthorMapper.class})
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target ="publisherQuery", source = "publisher")
    @Mapping(target="authorQueries", source = "authors")
    BookQuery toBookQuery(Book book);
}
