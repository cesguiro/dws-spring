package es.cesguiro.controller.user;

import es.cesguiro.controller.PaginatedResponse;
import es.cesguiro.config.PropertiesConfig;
import es.cesguiro.controller.user.webmodel.book.BookCollection;
import es.cesguiro.controller.user.webmodel.book.BookDetail;
import es.cesguiro.controller.user.webmodel.book.BookMapper;
import es.cesguiro.domain.model.Book;
import es.cesguiro.domain.model.ListWithCount;
import es.cesguiro.domain.usecase.book.BookCountUseCase;
import es.cesguiro.domain.usecase.book.BookFindByIsbnUseCase;
import es.cesguiro.domain.usecase.book.BookGetAllUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(BookUserController.URL)
public class BookUserController {

    public static final String URL = "/api/books";
    /*@Value("${app.base.url}")
    private String baseUrl;*/

    @Value("${app.pageSize.default}")
    private String defaultPageSize;

    private final BookGetAllUseCase bookGetAllUseCase;
    private final BookCountUseCase bookCountUseCase;
    private final BookFindByIsbnUseCase bookFindByIsbnUseCase;

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
        /*List<BookCollection> bookCollections = bookGetAllUseCase
                .execute(page - 1, pageSize)
                .getContent()
                .stream()
                .map(BookMapper.INSTANCE::toBookCollection)
                .toList();
        //int total = bookCountUseCase.execute();
        //int total = 100;

        PaginatedResponse<BookCollection> response = new PaginatedResponse<>(bookCollections, total, page, pageSize, baseUrl + URL);
        return new ResponseEntity<>(response, HttpStatus.OK);*/

    }


   @GetMapping("/{isbn}")
    public ResponseEntity<BookDetail> findByIsbn(@PathVariable String isbn) {
        BookDetail bookDetail = BookMapper.INSTANCE.toBookDetail(bookFindByIsbnUseCase.execute(isbn));
        return new ResponseEntity<>(bookDetail, HttpStatus.OK);
    }

}
