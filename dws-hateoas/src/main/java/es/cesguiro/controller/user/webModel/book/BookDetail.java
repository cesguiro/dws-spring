package es.cesguiro.controller.user.webModel.book;

import es.cesguiro.controller.user.webModel.author.AuthorCollection;
import es.cesguiro.controller.user.webModel.publisher.PublisherCollection;

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
