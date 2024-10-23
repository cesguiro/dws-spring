package es.cesguiro.controller.admin;

import es.cesguiro.controller.admin.webmodel.book.BookCollection;
import es.cesguiro.controller.admin.webmodel.book.BookMapper;
import es.cesguiro.controller.common.PaginatedResponse;
import es.cesguiro.domain.usecase.book.admin.BookGetAllAdminUseCase;
import es.cesguiro.domain.usecase.book.common.BookCountUseCase;
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

    private final BookGetAllAdminUseCase bookGetAllAdminUseCase;
    private final BookCountUseCase bookCountUseCase;
    /*private final GetAllAdminUseCase getAllAdminUseCase;
    private final FindByIsbnAdminUseCase findByIsbnAdminUseCase;
    private final CountAdminUseCase countAdminUseCase;
    private final InsertAuthorsAdminUseCase insertAuthorsAdminUseCase;
    private final InsertGenresAdminUseCase insertGenresAdminUseCase;
    private final InsertAdminUseCase insertAdminUseCase;*/

    @GetMapping
    public ResponseEntity<PaginatedResponse<BookCollection>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) Integer size) {
        int pageSize = (size != null) ? size : Integer.parseInt(defaultPageSize);
        List<BookCollection> books = bookGetAllAdminUseCase
                .execute(page - 1, pageSize)
                .stream()
                .map(BookMapper.INSTANCE::toBookCollection)
                .toList();

        int total = bookCountUseCase.execute();

        PaginatedResponse<BookCollection> response = new PaginatedResponse<>(books, total, page, pageSize, baseUrl + URL);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*@GetMapping("/{isbn}")
    public ResponseEntity<Book> findByIsbn(@PathVariable String isbn) {
        Book book = findByIsbnAdminUseCase.execute(isbn);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/{id}/authors")
    public ResponseEntity<Void> insertAuthors(@PathVariable Integer id, @RequestBody List<Author> authors) {
        insertAuthorsAdminUseCase.execute(id, authors);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{id}/genres")
    public ResponseEntity<Void> insertGenres(@PathVariable Integer id, @RequestBody List<Genre> genres) {
        insertGenresAdminUseCase.execute(id, genres);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Book book) {
        insertAdminUseCase.execute(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }*/
}
