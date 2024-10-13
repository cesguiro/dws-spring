package es.cesguiro.domain.user.service.impl;

import es.cesguiro.common.exception.ResourceNotFoundException;
import es.cesguiro.domain.user.model.Book;
import es.cesguiro.domain.user.service.BookUserService;
import es.cesguiro.persistence.user.repository.BookUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookUserServiceImpl implements BookUserService {

    private final BookUserRepository bookUserRepository;

    @Override
    public List<Book> getAll() {
        return bookUserRepository.getAll();
    }

    @Override
    public Book findByIsbn(String isbn) {
        return bookUserRepository.findByIsbn(isbn).orElseThrow(() -> new ResourceNotFoundException("Book isbn " + isbn + " not found"));
    }

    @Override
    public List<Book> getAll(int page, int size) {
        return bookUserRepository.getAll(page, size);
    }

    @Override
    public int count() {
        return bookUserRepository.count();
    }
}
