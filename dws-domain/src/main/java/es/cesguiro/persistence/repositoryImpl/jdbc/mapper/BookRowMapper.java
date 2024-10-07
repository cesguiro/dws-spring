package es.cesguiro.persistence.repositoryImpl.jdbc.mapper;

import es.cesguiro.domain.model.query.BookQuery;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements CustomRowMapper<BookQuery> {

    private final CategoryRowMapper categoryRowMapper = new CategoryRowMapper();
    private final PublisherRowMapper publisherRowMapper = new PublisherRowMapper();

    @Override
    public BookQuery mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookQuery bookQuery = new BookQuery();
        bookQuery.setIsbn(rs.getString("books.isbn"));
        bookQuery.setTitle(rs.getString("books.title_es"));
        bookQuery.setSynopsis(rs.getString("books.synopsis_es"));
        bookQuery.setPrice(new BigDecimal(rs.getString("books.price")));
        bookQuery.setDiscount(rs.getFloat("books.discount"));
        bookQuery.setCover(rs.getString("books.cover"));
        if(this.existsColumn(rs, "publishers.id")) {
            bookQuery.setPublisher(publisherRowMapper.mapRow(rs, rowNum));
        }
        if(this.existsColumn(rs, "categories.id")) {
            bookQuery.setCategory(categoryRowMapper.mapRow(rs, rowNum));
        }
        return bookQuery;
    }
}
