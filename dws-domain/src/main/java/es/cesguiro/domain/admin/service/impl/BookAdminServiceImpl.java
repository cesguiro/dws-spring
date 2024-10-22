package es.cesguiro.domain.admin.service.impl;

import es.cesguiro.common.annotation.DomainService;
import es.cesguiro.common.exception.ResourceAlreadyExistsException;
import es.cesguiro.common.exception.ResourceNotFoundException;
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
    private final AuthorAdminRepository authorAdminRepository;
    private final GenreAdminRepository genreAdminRepository;
    private final PublisherAdminRepository publisherAdminRepository;
    private final CategoryAdminRepository categoryAdminRepository;

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
    public void insertAuthors(int idBook, List<Author> authors) {
        //recuperar libro
        Book book = bookAdminRepository.findById(idBook).orElseThrow(() -> new ResourceNotFoundException("Book " + idBook + " not found"));
        //recuperar autores a insertar
        List<Author> authorList = authorAdminRepository.findAllById(
                authors
                        .stream()
                        .map(Author::getId)
                        .toArray(Long[]::new)
        );
        //comprobar que todos los autores pasados existen
        if(authorList.size() != authors.size()) {
            throw new ResourceNotFoundException("Some authors were not found");
        }
        //añadir autores al libro
        authorList.forEach(book::addAuthor);
        //guardar book
        bookAdminRepository.save(book);
    }

    @Override
    public void insertGenres(Integer idBook, List<Genre> genres) {
        //recuperar libro
        Book book = bookAdminRepository.findById(idBook).orElseThrow(() -> new ResourceNotFoundException("Book " + idBook + " not found"));
        //recuperar géneros a insertar
        List<Genre> genreList = genreAdminRepository.findAllById(
                genres
                        .stream()
                        .map(Genre::getId)
                        .toArray(Long[]::new)
        );
        //comprobar que todos los géneros pasados existen
        if(genreList.size() != genres.size()) {
            throw new ResourceNotFoundException("Some genres were not found");
        }
        //añadir géneros al libro
        genreList.forEach(book::addGenre);
        //guardar book
        bookAdminRepository.save(book);
    }

    @Override
    public void insert(Book book) {
        if(bookAdminRepository.findByIsbn(book.getIsbn()).isPresent()) {
            throw new ResourceAlreadyExistsException("Isbn " + book.getIsbn() + " already exists");
        }
        //comprobar el publisher
        if(publisherAdminRepository.findById(book.getPublisher().getId()).isEmpty()) {
            throw new ResourceNotFoundException("Publisher " + book.getPublisher().getId() + " not found");
        }
        //comprobar la categoría
        if(categoryAdminRepository.findById(book.getCategory().getId()).isEmpty()) {
            throw new ResourceNotFoundException("Category " + book.getCategory().getId() + " not found");
        }
        //recuperar autores a insertar
        List<Author> authorList = authorAdminRepository.findAllById(
                book.getAuthors()
                        .stream()
                        .map(Author::getId)
                        .toArray(Long[]::new)
        );
        //comprobar que todos los autores pasados existen
        if(authorList.size() != book.getAuthors().size()) {
            throw new ResourceNotFoundException("Some authors were not found");
        }
        //añadir autores al libro
        book.setAuthors(new ArrayList<>());
        authorList.forEach(book::addAuthor);
        //recuperar géneros a insertar
        List<Genre> genreList = genreAdminRepository.findAllById(
                book.getGenres()
                        .stream()
                        .map(Genre::getId)
                        .toArray(Long[]::new)
        );
        //comprobar que todos los géneros pasados existen
        if(genreList.size() != book.getGenres().size()) {
            throw new ResourceNotFoundException("Some genres were not found");
        }
        //añadir géneros al libro
        book.setGenres(new ArrayList<>());
        genreList.forEach(book::addGenre);

        bookAdminRepository.save(book);

    }

}
