package es.cesguiro.domain.model.query;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Data
@NoArgsConstructor
public class BookQuery {

    private String isbn;
    private String title;
    private String synopsis;
    private BigDecimal price;
    private float discount;
    private String cover;
    private PublisherQuery publisherQuery;
    private CategoryQuery categoryQuery;
    private List<AuthorQuery> authorQueries;
    private List<GenreQuery> genreQueries;

    public BookQuery(String isbn, String title, String synopsis, BigDecimal price, float discount, String cover, PublisherQuery publisherQuery, CategoryQuery categoryQuery, List<AuthorQuery> authorQueries, List<GenreQuery> genreQueries) {
        this.isbn = isbn;
        this.title = title;
        this.synopsis = synopsis;
        setPrice(price);
        this.discount = discount;
        this.cover = cover;
        this.publisherQuery = publisherQuery;
        this.categoryQuery = categoryQuery;
        this.authorQueries = authorQueries;
        this.genreQueries = genreQueries;
    }

    public void setPrice(BigDecimal price) {
        if (price == null) {
            price = new BigDecimal(0);
        }
        this.price = price.setScale(2, RoundingMode.HALF_UP);
    }
}
