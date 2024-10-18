package es.cesguiro.domain.admin.service.impl;

import es.cesguiro.common.exception.ResourceAlreadyExistsException;
import es.cesguiro.common.exception.ResourceNotFoundException;
import es.cesguiro.domain.admin.model.Author;
import es.cesguiro.domain.admin.model.Book;
import es.cesguiro.domain.admin.repository.AuthorAdminRepository;
import es.cesguiro.domain.admin.service.BookAdminService;
import es.cesguiro.domain.admin.repository.BookAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookAdminServiceImpl implements BookAdminService {

    private final BookAdminRepository bookAdminRepository;
    private final AuthorAdminRepository authorAdminRepository;

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
    public Book findByIsbn(String isbn) {
        return bookAdminRepository.findByIsbn(isbn).orElseThrow(() -> new ResourceNotFoundException("Book isbn " + isbn + " not found"));
    }

    @Override
    public void insertAuthors(int idBook, List<Author> authors) {
        //recuperar libro
        Book book = bookAdminRepository.findById(idBook).orElseThrow(() -> new ResourceNotFoundException("Book " + idBook + " not found"));
        //recuperar autores a insertar
        List<Author> authorsList = authorAdminRepository.findAllById(
                authors
                        .stream()
                        .map(Author::getId)
                        .toArray(Long[]::new)
        );
        //comprobar que todos los autores pasados existen
        if(authorsList.size() != authors.size()) {
            throw new ResourceNotFoundException("Some authors were not found");
        }
        //añadir autores al libro
        authorsList.forEach(book::addAuthor);
        //guardar book
        bookAdminRepository.save(book);
    }

    @Override
    public void insert(Book book) {
        if(bookAdminRepository.findByIsbn(book.getIsbn()).isPresent()) {
            throw new ResourceAlreadyExistsException("Isbn " + book.getIsbn() + " already exists");
        }
        bookAdminRepository.save(book);

    }
}
