package es.cesguiro.domain.service.impl;

import es.cesguiro.common.annotation.DomainService;
import es.cesguiro.domain.model.Publisher;
import es.cesguiro.domain.repository.PublisherRepository;
import es.cesguiro.domain.service.PublisherService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@DomainService
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    @Override
    public Optional<Publisher> findById(Long id) {
        return publisherRepository.findById(id);
    }
}
