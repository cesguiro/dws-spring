package es.cesguiro.domain.service;

import es.cesguiro.domain.model.Category;

import java.util.Optional;

public interface CategoryService {
    Optional<Category> findById(Long id);
}
