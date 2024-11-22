package es.cesguiro.domain.model;

import es.cesguiro.common.locale.LanguageUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private Long id;
    private String isbn;
    private String titleEs;
    private String titleEn;
    private String synopsisEs;
    private String synopsisEn;
    private BigDecimal price;
    private float discount;
    private String cover;
    private Publisher publisher;
    private Category category;
    private List<Genre> genres;
    private List<Author> authors;

    public String getTitle() {
        String language = LanguageUtils.getCurrentLanguage();
        if ("en".equals(language)) {
            return titleEn;
        }
        return titleEs;
    }

    public String getSynopsis() {
        String language = LanguageUtils.getCurrentLanguage();
        if ("en".equals(language)) {
            return synopsisEn;
        }
        return synopsisEs;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
    }
}
