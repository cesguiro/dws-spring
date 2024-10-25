package es.cesguiro.domain.user.service.impl;

import es.cesguiro.common.annotation.DomainService;
import es.cesguiro.common.exception.ResourceNotFoundException;
import es.cesguiro.domain.user.model.Book;
import es.cesguiro.domain.user.service.BookUserService;
import es.cesguiro.domain.user.repository.BookUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@DomainService
@RequiredArgsConstructor
public class BookUserServiceImpl implements BookUserService {

    private final BookUserRepository bookUserRepository;

    @Override
    public List<Book> getAll() {
        return bookUserRepository.getAll();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return bookUserRepository.findByIsbn(isbn);
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
