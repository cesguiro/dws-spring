package es.cesguiro.controller.model.book;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import es.cesguiro.controller.model.author.AuthorQuery;
import es.cesguiro.controller.model.publisher.PublisherQuery;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookQuery extends RepresentationModel<BookQuery> {

    private String isbn;
    private String title;
    private BigDecimal price;
    private float discount;
    private String cover;
    @JsonProperty("publisher")
    private PublisherQuery publisherQuery;
    @JsonProperty("authors")
    private List<AuthorQuery> authorQueries;
}
