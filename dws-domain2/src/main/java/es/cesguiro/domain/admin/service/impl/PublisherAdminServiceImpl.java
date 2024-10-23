package es.cesguiro.domain.admin.service.impl;

import es.cesguiro.common.annotation.DomainService;
import es.cesguiro.domain.admin.model.Publisher;
import es.cesguiro.domain.admin.repository.PublisherAdminRepository;
import es.cesguiro.domain.admin.service.PublisherAdminService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@DomainService
@RequiredArgsConstructor
public class PublisherAdminServiceImpl implements PublisherAdminService {

    private final PublisherAdminRepository publisherAdminRepository;

    @Override
    public Optional<Publisher> findById(Long id) {
        return publisherAdminRepository.findById(id);
    }
}
