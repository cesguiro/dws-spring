package es.cesguiro.domain.model.query;

import es.cesguiro.domain.exception.ResourceAlreadyExistsException;
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
    private PublisherQuery publisher;
    private CategoryQuery category;
    private List<AuthorQuery> authors;
    private List<GenreQuery> genres;

    public BookQuery(String isbn, String title, String synopsis, BigDecimal price, float discount, String cover, PublisherQuery publisher, CategoryQuery category, List<AuthorQuery> authors, List<GenreQuery> genres) {
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

    public void addGenre(GenreQuery genre) {
        if(this.genres.stream().anyMatch(g -> g.getSlug().equals(genre.getSlug()))) {
            throw new ResourceAlreadyExistsException("Genre " + genre.getName() + " already exists in book " + this.title);
        }
        this.genres.add(genre);
    }

    public void addAuthor(AuthorQuery author) {
        if (this.authors.stream().anyMatch(a -> a.getId() == author.getId())) {
            throw new ResourceAlreadyExistsException("Author " + author.getName() + " already exists in book " + this.title);
        }
        this.authors.add(author);
    }
}
