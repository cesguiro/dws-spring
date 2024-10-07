package es.cesguiro.controller.webModel.book;

import es.cesguiro.controller.webModel.author.AuthorMapper;
import es.cesguiro.controller.webModel.genre.GenreMapper;
import es.cesguiro.controller.webModel.publisher.PublisherMapper;
import es.cesguiro.domain.model.query.BookQuery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PublisherMapper.class, AuthorMapper.class, GenreMapper.class})
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookCollection toBookCollection(BookQuery bookQuery);

    @Mapping(target ="publisherCollection", source = "publisher")
    @Mapping(target="authorsCollection", source = "authors")
    @Mapping(target = "category", source = "category.name")
    @Mapping(target = "genres", source = "genres")
    BookDetail toBookDetail(BookQuery bookQuery);

}
