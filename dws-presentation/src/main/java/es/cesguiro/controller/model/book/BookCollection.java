package es.cesguiro.controller.model.book;

import es.cesguiro.controller.model.author.AuthorCollection;
import es.cesguiro.controller.model.publisher.PublisherCollection;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.List;

public record BookCollection(
        String isbn,
        String title,
        BigDecimal price,
        float discount,
        String cover
) { }
