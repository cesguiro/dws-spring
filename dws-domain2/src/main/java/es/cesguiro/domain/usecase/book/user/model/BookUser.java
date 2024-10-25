package es.cesguiro.domain.usecase.book.user.model;

import es.cesguiro.common.locale.LanguageUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Data
@NoArgsConstructor
public class BookUser {

    private String isbn;
    private String title;
    private String synopsis;
    private BigDecimal price;
    private float discount;
    private String cover;
    private PublisherUser publisher;
    private CategoryUser category;
    private List<AuthorUser> authors;
    private List<GenreUser> genres;

    public BookUser(String isbn, String title, String synopsis, BigDecimal price, float discount, String cover, PublisherUser publisher, CategoryUser category, List<AuthorUser> authors, List<GenreUser> genres) {
        this.isbn = isbn;
        this.title = title;
        this.synopsis = synopsis;
        setPrice(price);
        this.discount = discount;
        this.cover = cover;
        this.publisher = publisher;
        this.category = category;
        this.authors = authors;
        this.genres = genres;
    }

    public void setPrice(BigDecimal price) {
        if (price == null) {
            price = new BigDecimal(0);
        }
        this.price = price.setScale(2, RoundingMode.HALF_UP);
    }

}
