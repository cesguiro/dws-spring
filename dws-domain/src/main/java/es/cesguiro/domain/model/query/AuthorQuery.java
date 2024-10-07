package es.cesguiro.domain.model.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorQuery {

    private long id;
    private String name;
    private String nationality;
    private String biography;
    private int birthYear;
    private int deathYear;
}
