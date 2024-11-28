package es.cesguiro.controller.webmodel.book;


import java.math.BigDecimal;

public record BookUserCollection(
        String isbn,
        String title,
        BigDecimal price,
        float discount,
        String cover
) { }
