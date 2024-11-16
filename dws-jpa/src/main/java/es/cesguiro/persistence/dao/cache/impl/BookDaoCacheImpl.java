package es.cesguiro.persistence.dao.cache.impl;

import es.cesguiro.domain.model.Book;
import es.cesguiro.persistence.dao.cache.BookDaoCache;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BookDaoCacheImpl implements BookDaoCache {

    private final Map<String, Book> cache = new ConcurrentHashMap<>();
    private final Map<String, Long> expiration = new ConcurrentHashMap<>();
    private static final long TTL = 600_000L; // 10 minutos en milisegundos

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        Long expirationTime = expiration.get(isbn);
        if(expirationTime != null && expirationTime >= System.currentTimeMillis()) {
            System.out.println("Retrieved from cache: " + isbn);
            return Optional.ofNullable(cache.get(isbn));
        }
        cache.remove(isbn);
        expiration.remove(isbn);
        return Optional.empty();
    }

    @Override
    public void save(Book book) {
        cache.put(book.getIsbn(), book);
        expiration.put(book.getIsbn(), System.currentTimeMillis() + TTL);
    }

    @Override
    public void clearCache() {
        cache.clear();
        expiration.clear();
    }
}
