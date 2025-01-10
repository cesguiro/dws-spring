package es.cesguiro.persistence.dao.db.impl.jpa.repository;

import es.cesguiro.persistence.dao.db.impl.jpa.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenreJpaRepository extends JpaRepository<GenreEntity, Long> {

    @Query(value = "SELECT g.* FROM genres g " +
            "JOIN books_genres bg ON g.id = bg.genre_id " +
            "JOIN books b ON bg.book_id = b.id " +
            "WHERE b.isbn = :isbn", nativeQuery = true)
    List<GenreEntity> findByBooksIsbn(String isbn);


    @Query(value = "SELECT g.* FROM genres g " +
            "JOIN books_genres bg ON g.id = bg.genre_id " +
            "WHERE bg.book_id = :id", nativeQuery = true)
    List<GenreEntity> findByBooksId(long id);
}
