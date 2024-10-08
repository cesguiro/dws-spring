package es.cesguiro.domain.service.impl;

import es.cesguiro.common.annotations.DomainService;
import es.cesguiro.domain.exception.ResourceNotFoundException;
import es.cesguiro.domain.model.query.BookQuery;
import es.cesguiro.domain.service.BookService;
import es.cesguiro.domain.repository.BookRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<BookQuery> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public BookQuery findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new ResourceNotFoundException("Book isbn " + isbn + " not found"));
    }

}