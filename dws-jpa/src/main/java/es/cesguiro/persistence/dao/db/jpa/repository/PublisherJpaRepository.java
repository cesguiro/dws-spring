package es.cesguiro.persistence.dao.db.jpa.repository;

import es.cesguiro.persistence.dao.db.jpa.entity.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherJpaRepository extends JpaRepository<PublisherEntity, Long> {
}
