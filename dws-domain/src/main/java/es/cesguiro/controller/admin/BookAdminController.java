package es.cesguiro.controller.admin;

import es.cesguiro.controller.admin.webmodel.book.BookCollection;
import es.cesguiro.controller.admin.webmodel.book.BookMapper;
import es.cesguiro.controller.common.PaginatedResponse;
import es.cesguiro.domain.admin.model.Author;
import es.cesguiro.domain.admin.model.Book;
import es.cesguiro.domain.admin.model.Genre;
import es.cesguiro.domain.admin.service.BookAdminService;
import es.cesguiro.domain.admin.usecase.FindBookByIsbnUseCase;
import es.cesguiro.domain.admin.usecase.GetAllBooksUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(BookAdminController.URL)
public class BookAdminController {

    public static final String URL = "/api/admin/books";
    @Value("${app.base.url}")
    private String baseUrl;

    @Value("${app.pageSize.default}")
    private String defaultPageSize;

    //private final BookAdminService bookAdminService;
    private final GetAllBooksUseCase getAllBooksUseCase;
    private final FindBookByIsbnUseCase findByIsbnUseCase;

    @GetMapping
    public ResponseEntity<PaginatedResponse<BookCollection>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) Integer size) {
        int pageSize = (size != null) ? size : Integer.parseInt(defaultPageSize);
        List<BookCollection> books = getAllBooksUseCase
                .execute(page - 1, pageSize)
                .stream()
                .map(BookMapper.INSTANCE::toBookCollection)
                .toList();

        int total = bookAdminService.count();

        PaginatedResponse<BookCollection> response = new PaginatedResponse<>(books, total, page, pageSize, baseUrl + URL);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> findByIsbn(@PathVariable String isbn) {
        Book book = findByIsbnUseCase.execute(isbn);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/{id}/authors")
    public ResponseEntity<Void> insertAuthors(@PathVariable Integer id, @RequestBody List<Author> authors) {
        bookAdminService.insertAuthors(id, authors);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{id}/genres")
    public ResponseEntity<Void> insertGenres(@PathVariable Integer id, @RequestBody List<Genre> genres) {
        bookAdminService.insertGenres(id, genres);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Book book) {
        bookAdminService.insert(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
