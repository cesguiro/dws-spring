package es.cesguiro.persistence.repository.impl.jdbc.mapper;

import es.cesguiro.domain.model.Book;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements CustomRowMapper<Book>{

    private final CategoryRowMapper categoryRowMapper = new CategoryRowMapper();
    private final PublisherRowMapper publisherRowMapper = new PublisherRowMapper();

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setIsbn(rs.getString("books.isbn"));
        book.setTitle(rs.getString("books.title_es"));
        book.setSynopsis(rs.getString("books.synopsis_es"));
        book.setPrice(new BigDecimal(rs.getString("books.price")));
        book.setDiscount(rs.getFloat("books.discount"));
        book.setCover(rs.getString("books.cover"));
        if(this.existsColumn(rs, "publishers.id")) {
            book.setPublisher(publisherRowMapper.mapRow(rs, rowNum));
        }
        if(this.existsColumn(rs, "categories.id")) {
            book.setCategory(categoryRowMapper.mapRow(rs, rowNum));
        }
        return book;
    }
}
