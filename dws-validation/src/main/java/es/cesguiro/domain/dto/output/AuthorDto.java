package es.cesguiro.domain.dto.output;

public record AuthorDto(
        int id,
        String name,
        String nationality,
        String biography,
        int birthYear,
        int deathYear
) {
}
