package es.cesguiro.persistence.repository.impl;

import es.cesguiro.domain.model.Category;
import es.cesguiro.domain.repository.CategoryRepository;
import es.cesguiro.persistence.dao.db.CategoryDaoDb;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryDaoDb categoryDaoDb;

    @Override
    public Optional<Category> findById(Long id) {
        return categoryDaoDb.findById(id);
    }
}
