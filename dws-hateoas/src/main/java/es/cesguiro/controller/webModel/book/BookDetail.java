package es.cesguiro.controller.webModel.book;

import es.cesguiro.controller.webModel.author.AuthorCollection;
import es.cesguiro.controller.webModel.publisher.PublisherCollection;

import java.math.BigDecimal;
import java.util.List;

public record BookDetail(
        String isbn,
        String title,
        BigDecimal price,
        float discount,
        String cover,
        PublisherCollection publisherCollection,
        List<AuthorCollection> authorsCollection
) {
}
