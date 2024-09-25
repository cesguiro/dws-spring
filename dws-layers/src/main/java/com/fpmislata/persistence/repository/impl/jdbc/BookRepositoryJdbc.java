package com.fpmislata.persistence.repository.impl.jdbc;

import com.fpmislata.domain.model.Book;
import com.fpmislata.domain.model.Category;
import com.fpmislata.persistence.repository.BookRepository;
import com.fpmislata.persistence.repository.impl.jdbc.mapper.BookRowMapper;
import com.fpmislata.persistence.repository.impl.jdbc.mapper.CategoryRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJdbc implements BookRepository {

    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<Book> getAll()
    {
        String sql = "SELECT * FROM books";
        return jdbcTemplate.query(sql, new BookRowMapper());
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        String sql = """
                SELECT * FROM books
                LEFT JOIN categories ON books.category_id = categories.id
                LEFT JOIN publishers ON books.publisher_id = publishers.id
                AND books.isbn = ?
           """;
        try {
            Book book = jdbcTemplate.queryForObject(sql, new BookRowMapper(), isbn);
            CategoryRowMapper categoryRowMapper = new CategoryRowMapper();
            Category category = categoryRowMapper.mapRow((ResultSet) book, 0);
            return Optional.of(book);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
