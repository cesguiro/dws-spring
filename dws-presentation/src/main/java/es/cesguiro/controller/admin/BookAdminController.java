package es.cesguiro.controller.admin;

import es.cesguiro.controller.admin.webmodel.book.BookCollection;
import es.cesguiro.controller.admin.webmodel.book.BookMapper;
import es.cesguiro.controller.common.PaginatedResponse;
import es.cesguiro.domain.admin.model.Book;
import es.cesguiro.domain.admin.service.BookAdminService;
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

    private final BookAdminService bookAdminService;

    @GetMapping
    public ResponseEntity<PaginatedResponse<BookCollection>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) Integer size) {
        int pageSize = (size != null) ? size : Integer.parseInt(defaultPageSize);
        List<BookCollection> books = bookAdminService
                .getAll(page - 1, pageSize)
                .stream()
                .map(BookMapper.INSTANCE::toBookCollection)
                .toList();

        int total = bookAdminService.count();

        PaginatedResponse<BookCollection> response = new PaginatedResponse<>(books, total, page, pageSize, baseUrl + URL);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> findByIsbn(@PathVariable String isbn) {
        Book book = bookAdminService.findByIsbn(isbn);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}
