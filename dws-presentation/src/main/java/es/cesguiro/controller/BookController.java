package es.cesguiro.controller;

import es.cesguiro.controller.model.book.BookCollection;
import es.cesguiro.controller.model.book.BookDetail;
import es.cesguiro.controller.model.book.BookMapper;
import es.cesguiro.domain.model.Book;
import es.cesguiro.domain.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(BookController.URL)
@RequiredArgsConstructor
public class BookController {

    public static final String URL = "/books";

    private final BookService bookService;

    @GetMapping
    public List<BookCollection> getAll() {
        return bookService.getAll().stream().map(BookMapper.INSTANCE::toBookCollection).toList();
    }


    @GetMapping("/{isbn}")
    public BookDetail findByIsbn(@PathVariable String isbn) {
        return BookMapper.INSTANCE.toBookDetail(bookService.findByIsbn(isbn));
    }
}
