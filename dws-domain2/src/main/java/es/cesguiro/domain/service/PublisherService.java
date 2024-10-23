package es.cesguiro.domain.service;

import es.cesguiro.domain.model.Publisher;

import java.util.Optional;

public interface PublisherService {
    Optional<Publisher> findById(Long id);
}
