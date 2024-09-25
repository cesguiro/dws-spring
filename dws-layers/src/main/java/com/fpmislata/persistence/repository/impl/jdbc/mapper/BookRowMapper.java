package com.fpmislata.persistence.repository.impl.jdbc.mapper;

import com.fpmislata.domain.model.Book;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fpmislata.domain.model.Category;
import com.fpmislata.domain.model.Genre;
import com.fpmislata.domain.model.Publisher;
import org.springframework.jdbc.core.RowMapper;

public class BookRowMapper implements RowMapper<Book>{

    private final CategoryRowMapper categoryRowMapper = new CategoryRowMapper();

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setIsbn(rs.getString("books.isbn"));
        book.setTitle(rs.getString("books.title_es"));
        book.setSynopsis(rs.getString("books.synopsis_es"));
        book.setPrice(new BigDecimal(rs.getString("books.price")));
        book.setDiscount(rs.getFloat("books.discount"));
        book.setCover(rs.getString("books.cover"));
        return book;
    }
}
