package es.cesguiro.controller;

import es.cesguiro.domain.model.Book;
import es.cesguiro.domain.service.BookService;
import lombok.RequiredArgsConstructor;
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
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{isbn}")
    public Book findByIsbn(@PathVariable String isbn) {
        return bookService.findByIsbn(isbn);
    }
}
