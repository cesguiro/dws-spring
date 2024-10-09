package es.cesguiro.controller;

import es.cesguiro.controller.webModel.book.BookCollection;
import es.cesguiro.controller.webModel.book.BookDetail;
import es.cesguiro.controller.webModel.book.BookMapper;
import es.cesguiro.domain.model.command.BookCommand;
import es.cesguiro.domain.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(BookController.URL)
@RequiredArgsConstructor
public class BookController {

    public static final String URL = "/api/books";

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookCollection>> getAll() {
        List<BookCollection> bookCollections = bookService
                .getAll()
                .stream()
                .map(BookMapper.INSTANCE::toBookCollection)
                .toList();
        //return ResponseEntity.ok(bookCollections);
        return new ResponseEntity<>(bookCollections, HttpStatus.OK);
    }


    @GetMapping("/{isbn}")
    public ResponseEntity<BookDetail> findByIsbn(@PathVariable String isbn) {
        BookDetail bookDetail = BookMapper.INSTANCE.toBookDetail(bookService.findByIsbn(isbn));
        return new ResponseEntity<>(bookDetail, HttpStatus.OK);
    }

    @PostMapping("/{isbn}/genres/{genreSlug}")
    public ResponseEntity<Void> addGenre(@PathVariable String isbn, @PathVariable String genreSlug) {
        bookService.addGenre(isbn, genreSlug);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{isbn}/authors/{id}")
    public ResponseEntity<Void> addAuthor(@PathVariable String isbn, @PathVariable long id) {
        bookService.addAuthor(isbn, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody BookCommand bookCommand) {
        bookService.insert(bookCommand);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
