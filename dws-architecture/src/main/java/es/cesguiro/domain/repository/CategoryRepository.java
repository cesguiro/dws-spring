package es.cesguiro.domain.repository;

import es.cesguiro.domain.model.Category;

import java.util.Optional;

public interface CategoryRepository {
    Optional<Category> findById(Long id);
}
