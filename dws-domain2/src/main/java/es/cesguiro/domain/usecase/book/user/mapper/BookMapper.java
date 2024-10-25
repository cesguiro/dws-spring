package es.cesguiro.domain.usecase.book.user.mapper;

import es.cesguiro.domain.usecase.book.user.model.BookUser;
import es.cesguiro.domain.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PublisherMapper.class, GenreMapper.class, AuthorMapper.class, CategoryMapper.class})
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookUser toBookUser(Book book);

}
