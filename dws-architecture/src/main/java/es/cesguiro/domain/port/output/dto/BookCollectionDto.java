package es.cesguiro.domain.port.output.dto;

import java.math.BigDecimal;

public record BookCollectionDto(
        String isbn,
        String title,
        BigDecimal price,
        Float discount,
        String cover
) {
}
