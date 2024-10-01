package es.cesguiro.domain.service.impl;

import es.cesguiro.domain.model.Book;
import es.cesguiro.domain.service.BookService;
import es.cesguiro.persistence.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public Book findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new RuntimeException("Book not found"));
    }
}
