package es.cesguiro.domain.service.impl;

import es.cesguiro.common.annotations.DomainService;
import es.cesguiro.domain.exception.InsertResourceException;
import es.cesguiro.domain.exception.ResourceAlreadyExistsException;
import es.cesguiro.domain.exception.ResourceNotFoundException;
import es.cesguiro.domain.model.command.BookCommand;
import es.cesguiro.domain.model.query.AuthorQuery;
import es.cesguiro.domain.model.query.BookQuery;
import es.cesguiro.domain.model.query.GenreQuery;
import es.cesguiro.domain.repository.AuthorRepository;
import es.cesguiro.domain.repository.GenreRepository;
import es.cesguiro.domain.service.BookService;
import es.cesguiro.domain.repository.BookRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    @Override
    public List<BookQuery> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public BookQuery findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new ResourceNotFoundException("Book isbn " + isbn + " not found"));
    }

    @Override
    public void addGenre(String isbn, String genreSlug) {
        BookQuery book = this.findByIsbn(isbn);
        GenreQuery genre = genreRepository
                .findBySlug(genreSlug)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Genre slug " + genreSlug + " not found")
                );
        book.addGenre(genre);
        bookRepository.addGenre(isbn, genre.getId());
    }

    @Override
    public void addAuthor(String isbn, long authorId) {
        BookQuery book = this.findByIsbn(isbn);
        AuthorQuery author = authorRepository
                .findById(authorId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Author id " + authorId + " not found")
                );
        book.addAuthor(author);
        bookRepository.addAuthor(isbn, authorId);
    }

    @Override
    public void insert(BookCommand bookCommand) {
        if (bookRepository.findByIsbn(bookCommand.getIsbn()).isPresent()) {
            throw new ResourceAlreadyExistsException("Book isbn " + bookCommand.getIsbn() + " already exists");
        }
        //insertar el libro
        BookQuery book = bookRepository.insert(bookCommand).orElseThrow(
                () -> new InsertResourceException("Error inserting book")
        );
        //insertar los autores
        bookCommand.getAuthors().forEach(author -> {
            AuthorQuery authorQuery = authorRepository
                    .findById(author.getId())
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Author id " + author.getId() + " not found")
                    );
            book.addAuthor(authorQuery);
            bookRepository.addAuthor(book.getIsbn(), author.getId());
        });
        //insertar los géneros
        bookCommand.getGenres().forEach(genre -> {
            GenreQuery genreQuery = genreRepository
                    .findBySlug(genre.getSlug())
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Genre slug " + genre.getSlug() + " not found")
                    );
            book.addGenre(genreQuery);
            bookRepository.addGenre(book.getIsbn(), genreQuery.getId());
        });
    }
}
