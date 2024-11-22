package es.cesguiro.persistence.repository.impl;

import es.cesguiro.domain.model.Publisher;
import es.cesguiro.domain.repository.PublisherRepository;
import es.cesguiro.persistence.dao.db.PublisherDaoDb;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PublisherRepositoryImpl implements PublisherRepository {

    private final PublisherDaoDb publisherDaoDb;

    @Override
    public Optional<Publisher> findById(Long id) {
        return publisherDaoDb.findById(id);
    }
}
