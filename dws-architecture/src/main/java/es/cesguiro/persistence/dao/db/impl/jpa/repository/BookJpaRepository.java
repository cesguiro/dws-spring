package es.cesguiro.persistence.dao.db.impl.jpa.repository;

import es.cesguiro.persistence.dao.db.impl.jpa.entity.BookEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookJpaRepository extends JpaRepository<BookEntity, Long> {

    /*@Query("SELECT b FROM BookEntity b " +
            "JOIN FETCH b.publisher p " +
            "JOIN FETCH b.category c " +
            "WHERE b.isbn = :isbn")*/
    @EntityGraph(attributePaths = {"publisher", "category"})
    BookEntity findByIsbn(String isbn);

    @Override
    @EntityGraph(attributePaths = {"publisher", "category"})
    Optional<BookEntity> findById(Long id);

}
