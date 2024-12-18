package es.cesguiro.persistence.dao.db.impl.jpa;

import es.cesguiro.domain.model.Author;
import es.cesguiro.domain.model.Book;
import es.cesguiro.domain.model.Genre;
import es.cesguiro.domain.model.ListWithCount;
import es.cesguiro.persistence.dao.db.BookDaoDb;
import es.cesguiro.persistence.dao.db.impl.jpa.entity.BookEntity;
import es.cesguiro.persistence.dao.db.impl.jpa.mapper.AuthorJpaMapper;
import es.cesguiro.persistence.dao.db.impl.jpa.mapper.BookJpaMapper;
import es.cesguiro.persistence.dao.db.impl.jpa.mapper.GenreJpaMapper;
import es.cesguiro.persistence.dao.db.impl.jpa.repository.BookJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Primary
@RequiredArgsConstructor
public class BookDaoJpa implements BookDaoDb {

    private final BookJpaRepository bookJpaRepository;

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.ofNullable(bookJpaRepository.findByIsbn(isbn))
                .map(BookJpaMapper.INSTANCE::toBookWithDetails);
    }

    @Override
    public void deleteAuthors(long id) {
        bookJpaRepository.findById(id)
                .ifPresent(bookEntity -> bookEntity.getAuthors().clear());
    }

    @Override
    public void insertAuthors(long id, List<Author> authors) {
        bookJpaRepository.findById(id)
                .ifPresent(bookEntity -> bookEntity.setAuthors(
                        authors.stream()
                                .map(AuthorJpaMapper.INSTANCE::toAuthorEntity)
                                .toList()
                ));
    }

    @Override
    public void deleteGenres(long id) {
        bookJpaRepository.findById(id)
                .ifPresent(bookEntity -> bookEntity.getGenres().clear());
    }

    @Override
    public void insertGenres(long id, List<Genre> genres) {
        bookJpaRepository.findById(id)
                .ifPresent(bookEntity -> bookEntity.setGenres(
                        genres.stream()
                                .map(GenreJpaMapper.INSTANCE::toGenreEntity)
                                .toList()
                ));
    }

    @Override
    public List<Book> getAll() {
        return bookJpaRepository
                .findAll()
                .stream()
                .map(BookJpaMapper.INSTANCE::toBook)
                .toList();
    }

    @Override
    public ListWithCount<Book> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BookEntity> bookPage= bookJpaRepository.findAll(pageable);
        return new ListWithCount<Book>(
                bookPage.stream()
                        .map(BookJpaMapper.INSTANCE::toBook)
                        .toList(),
                bookPage.getTotalElements()
        );
    }

    @Override
    public Optional<Book> findById(long id) {
        return bookJpaRepository.findById(id)
                .map(BookJpaMapper.INSTANCE::toBookWithDetails);
    }

    @Override
    public long insert(Book book) {
        return bookJpaRepository.save(BookJpaMapper.INSTANCE.toBookEntity(book)).getId();
    }

    @Override
    public void update(Book book) {
        bookJpaRepository.save(BookJpaMapper.INSTANCE.toBookEntity(book));
    }

    @Override
    public void delete(long id) {
        bookJpaRepository.deleteById(id);
    }

    @Override
    public long count() {
        return bookJpaRepository.count();
    }

    @Override
    public Book save(Book book) {
        BookEntity bookEntity = BookJpaMapper.INSTANCE.toBookEntity(book);
        return BookJpaMapper.INSTANCE.toBook(bookJpaRepository.save(bookEntity));
    }
}
