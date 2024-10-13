package es.cesguiro.controller.admin;

import es.cesguiro.controller.admin.webmodel.book.BookCollection;
import es.cesguiro.controller.admin.webmodel.book.BookMapper;
import es.cesguiro.controller.common.PaginatedResponse;
import es.cesguiro.domain.admin.model.Book;
import es.cesguiro.domain.admin.service.BookAdminService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(BookAdminController.URL)
public class BookAdminController {

    public static final String URL = "/api/admin/books";

    private final BookAdminService bookAdminService;

    @GetMapping
    public ResponseEntity<PaginatedResponse<BookCollection>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            HttpServletRequest request) {
        List<BookCollection> books = bookAdminService
                .getAll(page - 1, size)
                .stream()
                .map(book -> BookMapper.INSTANCE.toBookCollection(book))
                .toList();

        int total = bookAdminService.count();

        String baseUrl = request.getRequestURL().toString();

        PaginatedResponse<BookCollection> response = new PaginatedResponse<>(books, (int) total, page, size, baseUrl);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> findByIsbn(@PathVariable String isbn) {
        Book book = bookAdminService.findByIsbn(isbn);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}
