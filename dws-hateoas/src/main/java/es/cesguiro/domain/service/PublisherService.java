package es.cesguiro.domain.service;

import es.cesguiro.domain.model.Publisher;

public interface PublisherService {

    Publisher findBySlug(String slug);
}
