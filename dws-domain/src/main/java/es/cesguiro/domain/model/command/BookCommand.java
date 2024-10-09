package es.cesguiro.domain.model.command;

import es.cesguiro.domain.model.query.AuthorQuery;
import es.cesguiro.domain.model.query.CategoryQuery;
import es.cesguiro.domain.model.query.GenreQuery;
import es.cesguiro.domain.model.query.PublisherQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCommand {
    private String isbn;
    private String titleEs;
    private String titleEn;
    private String synopsisEs;
    private String synopsisEn;
    private BigDecimal price;
    private float discount;
    private String cover;
    private PublisherQuery publisher;
    private CategoryQuery category;
    private List<AuthorQuery> authors;
    private List<GenreQuery> genres;
}
