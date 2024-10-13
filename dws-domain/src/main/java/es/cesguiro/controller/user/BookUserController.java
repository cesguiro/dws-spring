package es.cesguiro.controller.user;

import es.cesguiro.controller.user.webModel.book.BookCollection;
import es.cesguiro.controller.user.webModel.book.BookDetail;
import es.cesguiro.controller.user.webModel.book.BookMapper;
import es.cesguiro.domain.user.model.command.BookCommand;
import es.cesguiro.domain.user.service.BookUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(BookUserController.URL)
@RequiredArgsConstructor
public class BookUserController {

    public static final String URL = "/api/books";

    private final BookUserService bookUserService;

    @GetMapping
    public ResponseEntity<List<BookCollection>> getAll() {
        List<BookCollection> bookCollections = bookUserService
                .getAll()
                .stream()
                .map(BookMapper.INSTANCE::toBookCollection)
                .toList();
        //return ResponseEntity.ok(bookCollections);
        return new ResponseEntity<>(bookCollections, HttpStatus.OK);
    }


    @GetMapping("/{isbn}")
    public ResponseEntity<BookDetail> findByIsbn(@PathVariable String isbn) {
        BookDetail bookDetail = BookMapper.INSTANCE.toBookDetail(bookUserService.findByIsbn(isbn));
        return new ResponseEntity<>(bookDetail, HttpStatus.OK);
    }

    @PostMapping("/{isbn}/genres/{genreSlug}")
    public ResponseEntity<Void> addGenre(@PathVariable String isbn, @PathVariable String genreSlug) {
        bookUserService.addGenre(isbn, genreSlug);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{isbn}/authors/{id}")
    public ResponseEntity<Void> addAuthor(@PathVariable String isbn, @PathVariable long id) {
        bookUserService.addAuthor(isbn, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody BookCommand bookCommand) {
        bookUserService.insert(bookCommand);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
