package es.cesguiro.domain.admin.repository;

import es.cesguiro.domain.admin.model.Publisher;

import java.util.Map;
import java.util.Optional;

public interface PublisherAdminRepository {
    Optional<Publisher> findById(Long id);
}
