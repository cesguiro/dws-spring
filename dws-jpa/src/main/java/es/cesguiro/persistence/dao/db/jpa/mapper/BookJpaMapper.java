package es.cesguiro.persistence.dao.db.jpa.mapper;

import es.cesguiro.controller.user.webmodel.author.AuthorMapper;
import es.cesguiro.controller.user.webmodel.genre.GenreMapper;
import es.cesguiro.controller.user.webmodel.publisher.PublisherMapper;
import es.cesguiro.domain.model.Book;
import es.cesguiro.persistence.dao.db.jpa.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(uses = {PublisherJpaMapper.class, AuthorJpaMapper.class, GenreJpaMapper.class, CategoryJpaMapper.class})
public interface BookJpaMapper {

    BookJpaMapper INSTANCE = Mappers.getMapper(BookJpaMapper.class);

    Book toBookWithDetails(BookEntity bookEntity);

    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "publisher", ignore = true)
    @Mapping(target = "category", ignore = true)
    Book toBook(BookEntity bookEntity);

    BookEntity toBookEntity(Book book);

}
