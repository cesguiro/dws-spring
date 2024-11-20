package es.cesguiro.persistence.dao.db.jpa.repository;

import es.cesguiro.persistence.dao.db.jpa.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookJpaRepository extends JpaRepository<BookEntity, Long> {



    @Query("SELECT b FROM BookEntity b " +
            "JOIN FETCH b.publisher p " +      // JOIN FETCH para cargar la relación publisher
            "JOIN FETCH b.category c " +       // JOIN FETCH para cargar la relación category
            "WHERE b.isbn = :isbn")
    BookEntity findByIsbn(String isbn);
}
