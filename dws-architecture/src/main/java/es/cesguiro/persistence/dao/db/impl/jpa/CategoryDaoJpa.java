package es.cesguiro.persistence.dao.db.impl.jpa;

import es.cesguiro.domain.model.Category;
import es.cesguiro.domain.model.ListWithCount;
import es.cesguiro.persistence.dao.db.CategoryDaoDb;
import es.cesguiro.persistence.dao.db.impl.jpa.entity.CategoryEntity;
import es.cesguiro.persistence.dao.db.impl.jpa.mapper.CategoryJpaMapper;
import es.cesguiro.persistence.dao.db.impl.jpa.repository.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Primary
public class CategoryDaoJpa implements CategoryDaoDb {

    private final CategoryJpaRepository categoryJpaRepository;

    @Override
    public List<Category> getAll() {
        return categoryJpaRepository
                .findAll()
                .stream()
                .map(CategoryJpaMapper.INSTANCE::toCategory)
                .toList();
    }

    @Override
    public ListWithCount<Category> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CategoryEntity> categoryPage = categoryJpaRepository.findAll(pageable);
        return new ListWithCount<Category>(
                categoryJpaRepository.findAll(pageable)
                        .stream()
                        .map(CategoryJpaMapper.INSTANCE::toCategory)
                        .toList(),
                categoryPage.getTotalElements()
        );

    }

    @Override
    public Optional<Category> findById(long id) {
        return categoryJpaRepository.findById(id)
                .map(CategoryJpaMapper.INSTANCE::toCategory);
    }

    @Override
    public long insert(Category category) {
        CategoryEntity categoryEntity = categoryJpaRepository
                .save(CategoryJpaMapper.INSTANCE.toCategoryEntity(category));
        return categoryEntity.getId();
    }

    @Override
    public void update(Category category) {
        categoryJpaRepository.save(CategoryJpaMapper.INSTANCE.toCategoryEntity(category));
    }

    @Override
    public void delete(long id) {
        categoryJpaRepository.deleteById(id);
    }

    @Override
    public long count() {
        return categoryJpaRepository.count();
    }

    @Override
    public Category save(Category category) {
        return CategoryJpaMapper.INSTANCE.toCategory(
                categoryJpaRepository.save(CategoryJpaMapper.INSTANCE.toCategoryEntity(category))
        );
    }
}
