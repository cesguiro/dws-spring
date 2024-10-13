package es.cesguiro.domain.user.service.impl;

import es.cesguiro.common.annotations.DomainService;
import es.cesguiro.common.exception.InsertResourceException;
import es.cesguiro.common.exception.ResourceAlreadyExistsException;
import es.cesguiro.common.exception.ResourceNotFoundException;
import es.cesguiro.domain.user.model.command.AuthorCommand;
import es.cesguiro.domain.user.model.command.BookCommand;
import es.cesguiro.domain.user.model.command.GenreCommand;
import es.cesguiro.domain.user.model.mapper.AuthorMapper;
import es.cesguiro.domain.user.model.mapper.BookMapper;
import es.cesguiro.domain.user.model.mapper.GenreMapper;
import es.cesguiro.domain.user.model.Book;
import es.cesguiro.domain.user.repository.AuthorRepository;
import es.cesguiro.domain.user.repository.GenreRepository;
import es.cesguiro.domain.user.service.BookUserService;
import es.cesguiro.domain.user.repository.BookRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class BookUserServiceImpl implements BookUserService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public Book findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new ResourceNotFoundException("Book isbn " + isbn + " not found"));
    }

    @Override
    public void addGenre(String isbn, String genreSlug) {
        BookCommand book = BookMapper.INSTANCE.toBookCommand(this.findByIsbn(isbn));
        GenreCommand genre = genreRepository
                .findBySlug(genreSlug)
                .map(GenreMapper.INSTANCE::toGenreCommand)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Genre slug " + genreSlug + " not found")
                );
        book.addGenre(genre);
        bookRepository.addGenre(book.getIsbn(), genre.getId());
    }

    @Override
    public void addAuthor(String isbn, long authorId) {
        BookCommand book = BookMapper.INSTANCE.toBookCommand(this.findByIsbn(isbn));
        AuthorCommand author = authorRepository
                .findById(authorId)
                .map(AuthorMapper.INSTANCE::toAuthorCommand)
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
        BookCommand book = bookRepository.insert(bookCommand).orElseThrow(
                () -> new InsertResourceException("Error inserting book")
        );

        //insertar los autores
        bookCommand.getAuthors().forEach(author -> {
            AuthorCommand authorCommand = authorRepository
                    .findById(author.getId())
                    .map(AuthorMapper.INSTANCE::toAuthorCommand)
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Author id " + author.getId() + " not found")
                    );
            book.addAuthor(authorCommand);
            bookRepository.addAuthor(book.getIsbn(), author.getId());
        });

        //insertar los géneros
        bookCommand.getGenres().forEach(genre -> {
            GenreCommand genreQuery = genreRepository
                    .findBySlug(genre.getSlug())
                    .map(GenreMapper.INSTANCE::toGenreCommand)
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Genre slug " + genre.getSlug() + " not found")
                    );
            book.addGenre(genreQuery);
            bookRepository.addGenre(book.getIsbn(), genreQuery.getId());
        });
    }
}
