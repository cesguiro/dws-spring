package es.cesguiro.persistence.dao.db.impl.jpa.repository;

import es.cesguiro.persistence.dao.db.impl.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long> {
}
