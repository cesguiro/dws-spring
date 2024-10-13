package es.cesguiro.data;

import es.cesguiro.domain.user.model.*;

import java.math.BigDecimal;
import java.util.List;

public class BookTestData {

    public static List<Book> getBooks() {
        List<Publisher> publishers = PublisherTestData.getPublishers();
        List<Author> authors = AuthorTestData.getAuthors();
        List<Category> categories = CategoryTestData.getCategories();
        List<Genre> genres = GenreTestData.getGenres();

        return List.of(
                new Book(
                        "1111",
                        "Book 1",
                        "synopsis book 1",
                        new BigDecimal("12.30"),
                        10f,
                        "cover1.jpg",
                        publishers.get(0),
                        categories.get(0),
                        List.of(authors.get(0), authors.get(1)),
                        List.of(genres.get(0), genres.get(1))
                ),
                new Book(
                        "2222",
                        "Book 2",
                        "synopsis book 2",
                        new BigDecimal("15.50"),
                        15f,
                        "cover2.jpg",
                        publishers.get(1),
                        categories.get(1),
                        List.of(authors.get(2)),
                        List.of(genres.get(1), genres.get(2))
                ),
                new Book(
                        "3333",
                        "Book 3",
                        "synopsis book 3",
                        new BigDecimal("20.00"),
                        20f,
                        "cover3.jpg",
                        publishers.get(0),
                        categories.get(2),
                        List.of(authors.get(0)),
                        List.of(genres.get(2))
                ),
                new Book(
                        "4444",
                        "Book 4",
                        "synopsis book 4",
                        new BigDecimal("25.00"),
                        25f,
                        "cover4.jpg",
                        publishers.get(1),
                        categories.get(0),
                        List.of(authors.get(1)),
                        List.of(genres.get(3))
                )
        );
    }
}
