package es.cesguiro.domain.admin.service;

import es.cesguiro.domain.admin.model.Category;

import java.util.Optional;

public interface CategoryAdminService {
    Optional<Category> findById(Long id);
}
