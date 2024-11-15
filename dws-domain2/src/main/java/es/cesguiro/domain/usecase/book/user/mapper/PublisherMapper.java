package es.cesguiro.domain.usecase.book.user.mapper;

import es.cesguiro.domain.model.Publisher;
import es.cesguiro.domain.usecase.book.user.model.PublisherUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PublisherMapper {

    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);

    PublisherUser toPublihserUser(Publisher publisher);

}
