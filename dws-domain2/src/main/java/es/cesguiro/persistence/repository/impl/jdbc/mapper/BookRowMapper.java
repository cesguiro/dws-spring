package es.cesguiro.persistence.repository.impl.jdbc.mapper;

import es.cesguiro.domain.model.Book;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements CustomRowMapper<Book> {

    private final PublisherRowMapper publisherRowMapper = new PublisherRowMapper();
    private final CategoryRowMapper categoryRowMapper = new CategoryRowMapper();

    @Override
    public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getLong("books.id"));
        book.setIsbn(resultSet.getString("books.isbn"));
        book.setTitleEs(resultSet.getString("books.title_es"));
        book.setTitleEn(resultSet.getString("books.title_en"));
        book.setSynopsisEs(resultSet.getString("books.synopsis_es"));
        book.setSynopsisEn(resultSet.getString("books.synopsis_en"));
        book.setPrice(new BigDecimal(resultSet.getString("books.price")));
        book.setDiscount(resultSet.getFloat("books.discount"));
        book.setCover(resultSet.getString("books.cover"));
        if(this.existsColumn(resultSet, "publishers.id")) {
            book.setPublisher(publisherRowMapper.mapRow(resultSet, rowNum));
        }
        if(this.existsColumn(resultSet, "categories.id")) {
            book.setCategory(categoryRowMapper.mapRow(resultSet, rowNum));
        }
        return book;
    }
}
