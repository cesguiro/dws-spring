package es.cesguiro.domain.admin.service.impl;

import es.cesguiro.common.annotation.DomainService;
import es.cesguiro.domain.admin.model.Category;
import es.cesguiro.domain.admin.repository.CategoryAdminRepository;
import es.cesguiro.domain.admin.service.CategoryAdminService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@DomainService
@RequiredArgsConstructor
public class CategoryAdminServiceImpl implements CategoryAdminService {

    private final CategoryAdminRepository categoryAdminRepository;

    @Override
    public Optional<Category> findById(Long id) {
        return categoryAdminRepository.findById(id);
    }
}
