package es.cesguiro.domain.service.impl;

import es.cesguiro.domain.model.Publisher;
import es.cesguiro.domain.service.PublisherService;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Override
    public Publisher findBySlug(String slug) {
        return null;
    }
}
