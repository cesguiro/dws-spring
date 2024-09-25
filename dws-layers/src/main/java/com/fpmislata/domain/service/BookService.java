package com.fpmislata.domain.service;

import com.fpmislata.domain.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();

    Book findByIsbn(String isbn);
}
