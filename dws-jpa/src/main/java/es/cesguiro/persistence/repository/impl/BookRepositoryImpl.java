package es.cesguiro.persistence.repository.impl;

import es.cesguiro.domain.model.Book;
import es.cesguiro.domain.model.ListWithCount;
import es.cesguiro.domain.repository.BookRepository;
import es.cesguiro.persistence.dao.cache.BookDaoCache;
import es.cesguiro.persistence.dao.db.BookDaoDb;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private final BookDaoDb bookDaoDb;
    private final BookDaoCache bookDaoCache;


    @Override
    public List<Book> getAll() {
        return bookDaoDb.getAll();
    }

    @Override
    public ListWithCount<Book> getAll(int page, int size) {
        return bookDaoDb.getAll(page, size);
    }

    @Override
    public long count() {
        return bookDaoDb.count();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return bookDaoCache.findByIsbn(isbn).or(
                () -> {
                    System.out.println("Retrieved from db: " + isbn);
                    Optional<Book> book = bookDaoDb.findByIsbn(isbn);
                    book.ifPresent(bookDaoCache::save);
                    return book;
                }
        );
    }

    @Override
    public Optional<Book> findById(long id) {
        return bookDaoDb.findById(id);
    }

    @Override
    public void save(Book book) {
        bookDaoDb.save(book);
    }

    @Override
    public void delete(long id) {
        bookDaoDb.delete(id);
    }

}
