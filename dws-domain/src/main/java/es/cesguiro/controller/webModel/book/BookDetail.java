package es.cesguiro.controller.webModel.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import es.cesguiro.controller.webModel.author.AuthorCollection;
import es.cesguiro.controller.webModel.publisher.PublisherCollection;

import java.math.BigDecimal;
import java.util.List;

public record BookDetail(
        String isbn,
        String title,
        BigDecimal price,
        float discount,
        String synopsis,
        String cover,
        List<String> genres,
        String category,
        @JsonProperty("publisher") PublisherCollection publisherCollection,
        @JsonProperty("authors") List<AuthorCollection> authorsCollection
) {
}
