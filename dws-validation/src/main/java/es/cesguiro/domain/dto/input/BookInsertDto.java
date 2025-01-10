package es.cesguiro.domain.dto.input;

import java.math.BigDecimal;
import java.util.List;

public record BookInsertDto(
        String isbn,
        String titleEs,
        String titleEn,
        String synopsisEs,
        String synopsisEn,
        BigDecimal price,
        Float discount,
        String cover,
        long publisherId,
        long categoryId,
        List<Long> authorIds,
        List<Long> genreIds
) {
}
