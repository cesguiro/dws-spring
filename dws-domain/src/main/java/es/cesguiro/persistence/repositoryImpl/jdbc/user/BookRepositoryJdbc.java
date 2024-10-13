package es.cesguiro.persistence.repositoryImpl.jdbc.user;

import es.cesguiro.domain.user.model.command.BookCommand;
import es.cesguiro.domain.user.model.Book;
import es.cesguiro.domain.user.repository.AuthorRepository;
import es.cesguiro.domain.user.repository.BookRepository;
import es.cesguiro.domain.user.repository.GenreRepository;
import es.cesguiro.persistence.repositoryImpl.jdbc.user.mapper.BookRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJdbc implements BookRepository {

    private final JdbcTemplate jdbcTemplate;

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public List<Book> getAll()
    {
        String sql = """
                        SELECT * FROM books
                     """;
        return jdbcTemplate.query(sql, new BookRowMapper());
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        String sql = """
                SELECT * FROM books
                LEFT JOIN categories ON books.category_id = categories.id
                LEFT JOIN publishers ON books.publisher_id = publishers.id
                WHERE books.isbn = ?
           """;
        try {
            Book book = jdbcTemplate.queryForObject(sql, new BookRowMapper(), isbn);
            book.setAuthors(authorRepository.getByIsbnBook(isbn));
            book.setGenres(genreRepository.getByIsbnBook(isbn));
            return Optional.of(book);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void addGenre(String bookIsbn, long genreId) {
        String sql = """
                INSERT INTO books_genres (book_id, genre_id) VALUES ((SELECT id FROM books WHERE isbn = ?), ?)
           """;
        jdbcTemplate.update(sql, bookIsbn, genreId);
    }

    @Override
    public void addAuthor(String bookIsbn, long authorId) {
        String sql = """
                INSERT INTO books_authors (book_id, author_id) VALUES ((SELECT id FROM books WHERE isbn = ?), ?)
           """;
        jdbcTemplate.update(sql, bookIsbn, authorId);
    }

    @Override
    public Optional<BookCommand> insert(BookCommand bookCommand) {
        String sql = """
                INSERT INTO books (isbn, title_es, title_en, synopsis_es, synopsis_en, price, discount, cover, publisher_id, category_id)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
              """;


        jdbcTemplate.update(sql,
                bookCommand.getIsbn(),
                bookCommand.getTitleEs(),
                bookCommand.getTitleEn(),
                bookCommand.getSynopsisEs(),
                bookCommand.getSynopsisEn(),
                bookCommand.getPrice(),
                bookCommand.getDiscount(),
                bookCommand.getCover(),
                bookCommand.getPublisher().getId(),
                bookCommand.getCategory().getId()
        );

        return Optional.of(bookCommand);
        //return this.findByIsbn(bookCommand.getIsbn());
    }
}
