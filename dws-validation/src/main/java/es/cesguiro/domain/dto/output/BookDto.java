package es.cesguiro.domain.dto.output;

import java.math.BigDecimal;
import java.util.List;

public record BookDto(
    String isbn,
    String title,
    String synopsis,
    BigDecimal price,
    Float discount,
    String cover,
    PublisherDto publisher,
    CategoryDto category,
    List<GenreDto> genre,
    List<AuthorDto> author
) { }
