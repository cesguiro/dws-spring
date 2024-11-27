package es.cesguiro.controller;

import es.cesguiro.config.ApiConfig;
import es.cesguiro.config.PropertiesConfig;
import es.cesguiro.controller.user.BookUserController;
import es.cesguiro.controller.user.webmodel.book.BookCollection;
import es.cesguiro.controller.user.webmodel.book.BookDetail;
import es.cesguiro.controller.user.webmodel.book.BookMapper;
import es.cesguiro.domain.model.Book;
import es.cesguiro.domain.model.ListWithCount;
import es.cesguiro.domain.usecase.book.BookFindByIsbnUseCase;
import es.cesguiro.domain.usecase.book.BookGetAllUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.api.path}")
public class BookController {

    public static final String RESOURCE_PATH = "/books";
    //public static final String ADMIN_PATH = ApiConfig.getAdminPath() + RESOURCE_PATH;
    public static final String BASE_URL = ApiConfig.getBaseUrl() + RESOURCE_PATH;
    public static final String ADMIN_URL = ApiConfig.getAdminUrl() + RESOURCE_PATH;

    private final String DEFAULT_PAGE_SIZE = PropertiesConfig.getSetting("app.pageSize.default");


    private final BookGetAllUseCase bookGetAllUseCase;
    private final BookFindByIsbnUseCase bookFindByIsbnUseCase;



    @GetMapping(RESOURCE_PATH)
    public ResponseEntity<PaginatedResponse<BookCollection>> getAllUser(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) Integer size) {

        int pageSize = (size != null) ? size : Integer.parseInt(DEFAULT_PAGE_SIZE);
        //String baseUrl = PropertiesConfig.getSetting("app.base.url") + URL;
        ListWithCount<Book> bookList = bookGetAllUseCase.execute(page - 1, pageSize);
        PaginatedResponse<BookCollection> response = new PaginatedResponse<>(
                bookList
                        .getList()
                        .stream()
                        .map(BookMapper.INSTANCE::toBookCollection)
                        .toList(),
                bookList.getCount(), page, pageSize, BASE_URL);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("${app.admin.path}"+RESOURCE_PATH)
    public ResponseEntity<PaginatedResponse<es.cesguiro.controller.admin.webmodel.book.BookCollection>> getAllAdmin(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) Integer size) {

        int pageSize = (size != null) ? size : Integer.parseInt(defaultPageSize);
        ListWithCount<Book> bookList = bookGetAllUseCase.execute(page - 1, pageSize);
        PaginatedResponse<es.cesguiro.controller.admin.webmodel.book.BookCollection> response = new PaginatedResponse<>(
                bookList
                        .getList()
                        .stream()
                        .map(es.cesguiro.controller.admin.webmodel.book.BookMapper.INSTANCE::toBookCollection)
                        .toList(),
                bookList.getCount(), page, pageSize, BASE_URL);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @GetMapping("/{isbn}")
    public ResponseEntity<BookDetail> findByIsbn(@PathVariable String isbn) {
        BookDetail bookDetail = BookMapper.INSTANCE.toBookDetail(bookFindByIsbnUseCase.execute(isbn));
        return new ResponseEntity<>(bookDetail, HttpStatus.OK);
    }



}
