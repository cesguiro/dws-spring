package es.cesguiro.controller.webModel.book;

import es.cesguiro.controller.webModel.author.AuthorMapper;
import es.cesguiro.controller.webModel.publisher.PublisherMapper;
import es.cesguiro.domain.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PublisherMapper.class, AuthorMapper.class})
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookCollection toBookCollection(Book book);

    @Mapping(target ="publisherCollection", source = "publisher")
    @Mapping(target="authorsCollection", source = "authors")
    BookDetail toBookDetail(Book book);

    @Mapping(target ="publisherQuery", source = "publisher")
    @Mapping(target="authorQueries", source = "authors")
    BookQuery toBookQuery(Book book);
}
