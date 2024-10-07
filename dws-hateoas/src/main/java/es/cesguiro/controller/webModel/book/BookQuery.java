package es.cesguiro.controller.webModel.book;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import es.cesguiro.controller.webModel.author.AuthorQuery;
import es.cesguiro.controller.webModel.publisher.PublisherQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
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
