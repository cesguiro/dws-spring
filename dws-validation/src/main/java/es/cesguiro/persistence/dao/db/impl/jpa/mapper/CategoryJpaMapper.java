package es.cesguiro.persistence.dao.db.impl.jpa.mapper;

import es.cesguiro.domain.model.Category;
import es.cesguiro.persistence.dao.db.impl.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryJpaMapper {

    CategoryJpaMapper INSTANCE = Mappers.getMapper(CategoryJpaMapper.class);

    Category toCategory(CategoryEntity categoryEntity);

    CategoryEntity toCategoryEntity(Category category);

}
