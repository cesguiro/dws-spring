package es.cesguiro.persistence.dao.db.jpa.repository;

import es.cesguiro.persistence.dao.db.jpa.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJpaRepository extends JpaRepository<BookEntity, Long> {

    BookEntity findByIsbn(String isbn);
}
