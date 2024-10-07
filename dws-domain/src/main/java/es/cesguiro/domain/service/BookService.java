package es.cesguiro.domain.service;

import es.cesguiro.domain.model.query.BookQuery;

import java.util.List;

public interface BookService {
    List<BookQuery> getAll();

    BookQuery findByIsbn(String isbn);
}
