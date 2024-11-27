package es.cesguiro.controller.admin;

import es.cesguiro.config.PropertiesConfig;
import es.cesguiro.controller.admin.webmodel.book.BookCollection;
import es.cesguiro.controller.PaginatedResponse;
import es.cesguiro.controller.admin.webmodel.book.BookMapper;
import es.cesguiro.domain.model.Author;
import es.cesguiro.domain.model.Book;
import es.cesguiro.domain.model.Genre;
import es.cesguiro.domain.model.ListWithCount;
import es.cesguiro.domain.usecase.book.admin.*;
import es.cesguiro.domain.usecase.book.BookFindByIsbnUseCase;
import es.cesguiro.domain.usecase.book.BookGetAllUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.admin.path}/books")
public class BookAdminController {

    public static final String URL = PropertiesConfig.getSetting("app.admin.path") + "/books";
    private final String defaultPageSize = PropertiesConfig.getSetting("app.pageSize.default");

    private final BookGetAllUseCase bookGetAllUseCase;
    private final BookFindByIsbnUseCase bookFindByIsbnUseCase;
    private final BookInsertAuthorsUseCase bookInsertAuthorsUseCase;
    private final BookInsertGenresUseCase bookInsertGenresUseCase;
    private final BookInsertUseCase bookInsertUseCase;
    private final BookDeleteUseCase bookDeleteUseCase;

    @GetMapping
    public ResponseEntity<PaginatedResponse<BookCollection>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) Integer size) {

        int pageSize = (size != null) ? size : Integer.parseInt(defaultPageSize);
        String baseUrl = PropertiesConfig.getSetting("app.base.url") + URL;
        ListWithCount<Book> bookList = bookGetAllUseCase.execute(page - 1, pageSize);
        PaginatedResponse<BookCollection> response = new PaginatedResponse<>(
                bookList
                        .getList()
                        .stream()
                        .map(BookMapper.INSTANCE::toBookCollection)
                        .toList(),
                bookList.getCount(), page, pageSize, baseUrl);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> findByIsbn(@PathVariable String isbn) {
        Book book = bookFindByIsbnUseCase.execute(isbn);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/{id}/authors")
    public ResponseEntity<Void> insertAuthors(@PathVariable Integer id, @RequestBody List<Author> authors) {
        bookInsertAuthorsUseCase.execute(id, authors);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{id}/genres")
    public ResponseEntity<Void> insertGenres(@PathVariable Integer id, @RequestBody List<Genre> genres) {
        bookInsertGenresUseCase.execute(id, genres);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Book book) {
        bookInsertUseCase.execute(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookDeleteUseCase.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
