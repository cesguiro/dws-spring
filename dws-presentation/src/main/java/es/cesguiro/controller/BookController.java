package es.cesguiro.controller;

import es.cesguiro.controller.model.book.BookMapper;
import es.cesguiro.controller.model.book.BookQuery;
import es.cesguiro.domain.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(BookController.URL)
@RequiredArgsConstructor
public class BookController {

    public static final String URL = "/books";

    private final BookService bookService;

    /*@GetMapping
    public ResponseEntity<List<BookCollection>> getAll() {
        List<BookCollection> bookCollections = bookService
                .getAll()
                .stream()
                .map(BookMapper.INSTANCE::toBookCollection)
                .toList();
        //return ResponseEntity.ok(bookCollections);
        return new ResponseEntity<>(bookCollections, HttpStatus.OK);
    }*/

    @GetMapping
    public ResponseEntity<List<BookQuery>> getAll() {
        List<BookQuery> bookQueries = bookService
                .getAll()
                .stream()
                .map(BookMapper.INSTANCE::toBookQuery)
                .peek(bookQuery -> bookQuery.add(linkTo(methodOn(BookController.class).findByIsbn(bookQuery.getIsbn())).withSelfRel()))
                .toList();
        return new ResponseEntity<>(bookQueries, HttpStatus.OK);
    }



    /*@GetMapping("/{isbn}")
    public BookDetail findByIsbn(@PathVariable String isbn) {
        return BookMapper.INSTANCE.toBookDetail(bookService.findByIsbn(isbn));
    }*/

    @GetMapping("/{isbn}")
    public ResponseEntity<BookQuery> findByIsbn(@PathVariable String isbn) {
        BookQuery bookQuery = BookMapper.INSTANCE.toBookQuery(bookService.findByIsbn(isbn));
        bookQuery
                .getPublisherQuery()
                .add(
                        linkTo(methodOn(PublisherController.class).findBySlug(bookQuery.getPublisherQuery().getSlug())).withSelfRel()
                );
        bookQuery
                .getAuthorQueries()
                .forEach(authorQuery ->
                    authorQuery.add(linkTo(methodOn(AuthorController.class).findById(authorQuery.getId())).withSelfRel())
        );
        return new ResponseEntity<>(bookQuery, HttpStatus.OK);
    }

}
