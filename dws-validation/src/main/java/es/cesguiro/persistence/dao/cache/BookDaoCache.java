package es.cesguiro.persistence.dao.cache;

import es.cesguiro.domain.model.Book;

import java.util.Optional;

public interface BookDaoCache extends GenericDaoCache<Book>{

    Optional<Book> findByIsbn(String isbn);
}
