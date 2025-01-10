package es.cesguiro.domain.repository;

import es.cesguiro.domain.model.Publisher;

import java.util.Optional;

public interface PublisherRepository {
    Optional<Publisher> findById(Long id);
}
