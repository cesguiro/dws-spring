package es.cesguiro.domain.admin.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.cesguiro.common.annotation.DomainService;
import es.cesguiro.common.exception.ResourceAlreadyExistsException;
import es.cesguiro.domain.admin.model.Author;
import es.cesguiro.domain.admin.model.Book;
import es.cesguiro.domain.admin.model.Genre;
import es.cesguiro.domain.admin.repository.*;
import es.cesguiro.domain.admin.service.BookAdminService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DomainService
@RequiredArgsConstructor
public class BookAdminServiceImpl implements BookAdminService {

    private final BookAdminRepository bookAdminRepository;

    @Override
    public List<Book> getAll() {
        return bookAdminRepository.getAll();
    }

    @Override
    public List<Book> getAll(int page, int size) {
        return bookAdminRepository.getAll(page, size);
    }

    @Override
    public int count() {
        return bookAdminRepository.count();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return bookAdminRepository.findByIsbn(isbn);
    }

    @Override
    public Optional<Book> findById(long id) {
        return bookAdminRepository.findById(id);
    }

    @Override
    public void save(Book book) {
        bookAdminRepository.save(book);
    }

    @Override
    public void addAuthor(Book book, Author author) {
        if (book.getAuthors() == null) {
            book.setAuthors(new ArrayList<>());
        }
        if (book.getAuthors().contains(author)) {
            throw new ResourceAlreadyExistsException("Author " + author.getName() + "already exists");
        }
        book.addAuthor(author);
    }

    @Override
    public void addGenre(Book book, Genre genre) {
        if (book.getGenres() == null) {
            book.setGenres(new ArrayList<>());
        }
        if (book.getGenres().contains(genre)) {
            throw new ResourceAlreadyExistsException("Genre " + genre.getId() + "already exists");
        }
        book.addGenre(genre);
    }

}
