package es.cesguiro.domain.admin.repository;

import es.cesguiro.domain.admin.model.Category;

import java.util.Optional;

public interface CategoryAdminRepository {
    Optional<Category> findById(Long id);
}
