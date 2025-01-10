package es.cesguiro.domain.dto.output;

import java.math.BigDecimal;
import java.util.List;

public record BookCollectionDto(
        String isbn,
        String title,
        BigDecimal price,
        Float discount,
        String cover
) {
}
