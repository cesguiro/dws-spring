package es.cesguiro.controller.webmodel.book;

import es.cesguiro.controller.webmodel.author.AuthorMapper;
import es.cesguiro.controller.webmodel.genre.GenreMapper;
import es.cesguiro.controller.webmodel.publisher.PublisherMapper;
import es.cesguiro.domain.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PublisherMapper.class, AuthorMapper.class, GenreMapper.class})
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookUserCollection toBookUserCollection(Book book);

    BookAdminCollection toBookAdminCollection(Book book);

    @Mapping(target = "category", source = "category.name")
    BookUserDetail toBookUserDetail(Book book);

}
