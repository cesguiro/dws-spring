package es.cesguiro.persistence.dao.db.impl.jpa.repository;

import es.cesguiro.persistence.dao.db.impl.jpa.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorJpaRepository extends JpaRepository<AuthorEntity, Long> {


    @Query(value = "SELECT a.* FROM authors a " +
            "JOIN books_authors ba ON a.id = ba.author_id " +
            "JOIN books b ON ba.book_id = b.id " +
            "AND b.isbn = :isbn", nativeQuery = true)
    List<AuthorEntity> findByBooksIsbn(String isbn);

    @Query(value = "SELECT a.* FROM authors a " +
            "JOIN books_authors ba ON a.id = ba.author_id " +
            "AND ba.book_id = :id", nativeQuery = true)
    List<AuthorEntity> findByBooksId(@Param("id") Long id);

}
