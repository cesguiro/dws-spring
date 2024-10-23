package es.cesguiro.controller.user.webmodel.book;

import es.cesguiro.controller.user.webmodel.author.AuthorMapper;
import es.cesguiro.controller.user.webmodel.genre.GenreMapper;
import es.cesguiro.controller.user.webmodel.publisher.PublisherMapper;
import es.cesguiro.domain.user.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PublisherMapper.class, AuthorMapper.class, GenreMapper.class})
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookCollection toBookCollection(Book book);

    @Mapping(target ="publisherCollection", source = "publisher")
    @Mapping(target="authorsCollection", source = "authors")
    @Mapping(target = "category", source = "category.name")
    @Mapping(target = "genres", source = "genres")
    BookDetail toBookDetail(Book book);

}
