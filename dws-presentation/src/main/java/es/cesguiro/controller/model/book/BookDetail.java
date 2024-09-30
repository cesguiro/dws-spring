package es.cesguiro.controller.model.book;

import es.cesguiro.controller.model.author.AuthorCollection;
import es.cesguiro.controller.model.publisher.PublisherCollection;

import java.math.BigDecimal;
import java.util.List;

public record BookDetail(
        String isbn,
        String title,
        BigDecimal price,
        float discount,
        String cover,
        PublisherCollection publisherCollection,
        List<AuthorCollection> authorCollection
) {
}
