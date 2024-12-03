package es.cesguiro.controller.user.webmodel.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import es.cesguiro.controller.user.webmodel.author.AuthorCollection;
import es.cesguiro.controller.user.webmodel.publisher.PublisherCollection;

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
        PublisherCollection publisher,
        List<AuthorCollection> authors
) {
}
