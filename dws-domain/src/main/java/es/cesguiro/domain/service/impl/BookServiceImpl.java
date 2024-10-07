package es.cesguiro.domain.service.impl;

import es.cesguiro.domain.exception.ResourceNotFoundException;
import es.cesguiro.domain.model.query.BookQuery;
import es.cesguiro.domain.service.BookService;
import es.cesguiro.domain.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<BookQuery> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public BookQuery findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new ResourceNotFoundException("Book isbn " + isbn + " not found"));
    }
}
