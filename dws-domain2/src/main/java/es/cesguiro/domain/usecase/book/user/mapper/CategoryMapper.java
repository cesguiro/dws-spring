package es.cesguiro.domain.usecase.book.user.mapper;

import es.cesguiro.domain.usecase.book.user.model.CategoryUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryUser toCategoryUser(CategoryUser categoryUser);

}
