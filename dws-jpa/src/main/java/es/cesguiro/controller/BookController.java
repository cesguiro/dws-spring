package es.cesguiro.controller;

import es.cesguiro.config.ApiConfig;
import es.cesguiro.config.PropertiesConfig;
import es.cesguiro.controller.webmodel.PaginatedResponse;
import es.cesguiro.controller.webmodel.book.BookAdminCollection;
import es.cesguiro.controller.webmodel.book.BookMapper;
import es.cesguiro.controller.webmodel.book.BookUserCollection;
import es.cesguiro.controller.webmodel.book.BookUserDetail;
import es.cesguiro.domain.model.Author;
import es.cesguiro.domain.model.Book;
import es.cesguiro.domain.model.Genre;
import es.cesguiro.domain.model.ListWithCount;
import es.cesguiro.domain.usecase.book.BookFindByIsbnUseCase;
import es.cesguiro.domain.usecase.book.BookGetAllUseCase;
import es.cesguiro.domain.usecase.book.admin.BookDeleteUseCase;
import es.cesguiro.domain.usecase.book.admin.BookInsertAuthorsUseCase;
import es.cesguiro.domain.usecase.book.admin.BookInsertGenresUseCase;
import es.cesguiro.domain.usecase.book.admin.BookInsertUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.api.path}")
public class BookController extends BaseController {

    public static final String RESOURCE_PATH = "/books";
    public static final String RESOURCE_ADMIN_PATH = ADMIN_PATH + RESOURCE_PATH;
    public static final String BASE_URL = ApiConfig.getBaseUrl() + RESOURCE_PATH;


    private final BookGetAllUseCase bookGetAllUseCase;
    private final BookFindByIsbnUseCase bookFindByIsbnUseCase;
    private final BookInsertAuthorsUseCase bookInsertAuthorsUseCase;
    private final BookInsertGenresUseCase bookInsertGenresUseCase;
    private final BookInsertUseCase bookInsertUseCase;
    private final BookDeleteUseCase bookDeleteUseCase;

    @Override
    protected ListWithCount<Book> getList(int page, int size) {
        return bookGetAllUseCase.execute(page - 1, size);
    }

    @GetMapping(RESOURCE_PATH)
    public ResponseEntity<PaginatedResponse<BookUserCollection>> getAllUser(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) Integer size) {
        PaginatedResponse<BookUserCollection> response = this.getAll(page, size, BookMapper.INSTANCE::toBookUserCollection);
        return this.buildResponse(response, HttpStatus.OK);
    }

    @GetMapping(RESOURCE_ADMIN_PATH)
    public ResponseEntity<PaginatedResponse<BookAdminCollection>> getAllAdmin(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) Integer size) {
        PaginatedResponse<BookAdminCollection> response =
                this.getAll(
                        page, size,
                        BookMapper.INSTANCE::toBookAdminCollection,
                        this::getList,
                        BASE_URL
                );

        return this.buildResponse(response, HttpStatus.OK);
    }

    @GetMapping(RESOURCE_PATH+"/{isbn}")
    public ResponseEntity<BookUserDetail> findByIsbnUser(@PathVariable String isbn) {
        BookUserDetail bookDetail = BookMapper.INSTANCE.toBookUserDetail(bookFindByIsbnUseCase.execute(isbn));
        return this.buildResponse(bookDetail, HttpStatus.OK);
    }

    @GetMapping(RESOURCE_ADMIN_PATH+"/{isbn}")
    public ResponseEntity<Book> findByIsbnAdmin(@PathVariable String isbn) {
        return this.buildResponse(
                bookFindByIsbnUseCase.execute(isbn),
                HttpStatus.OK
        );
    }

    /****** ADMIN USE CASES **********
     ******                 **********/

    @PostMapping(RESOURCE_ADMIN_PATH+"/{id}/authors")
    public ResponseEntity<Void> insertAuthors(@PathVariable Integer id, @RequestBody List<Author> authors) {
        bookInsertAuthorsUseCase.execute(id, authors);
        return this.buildResponse(null, HttpStatus.CREATED);
    }

    @PostMapping(RESOURCE_ADMIN_PATH+"/{id}/genres")
    public ResponseEntity<Void> insertGenres(@PathVariable Integer id, @RequestBody List<Genre> genres) {
        bookInsertGenresUseCase.execute(id, genres);
        return this.buildResponse(null, HttpStatus.CREATED);
    }

    @PostMapping(RESOURCE_ADMIN_PATH)
    public ResponseEntity<Void> insert(@RequestBody Book book) {
        bookInsertUseCase.execute(book);
        return this.buildResponse(null, HttpStatus.CREATED);
    }

    @DeleteMapping(RESOURCE_ADMIN_PATH+"/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookDeleteUseCase.execute(id);
        return this.buildResponse(null, HttpStatus.NO_CONTENT);
    }


}
