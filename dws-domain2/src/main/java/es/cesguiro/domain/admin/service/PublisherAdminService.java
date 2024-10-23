package es.cesguiro.domain.admin.service;

import es.cesguiro.domain.admin.model.Publisher;

import java.util.Optional;

public interface PublisherAdminService {
    Optional<Publisher> findById(Long id);
}
