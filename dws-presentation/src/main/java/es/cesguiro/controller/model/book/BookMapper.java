package es.cesguiro.controller.model.book;

import es.cesguiro.controller.model.author.AuthorMapper;
import es.cesguiro.domain.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import es.cesguiro.controller.model.publisher.PublisherMapper;

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
