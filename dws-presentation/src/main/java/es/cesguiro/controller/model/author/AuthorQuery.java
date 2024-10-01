package es.cesguiro.controller.model.author;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorQuery extends RepresentationModel<AuthorQuery> {

    @JsonIgnore
    private Long id;
    private String name;
    @JsonIgnore
    private String nationality;
    @JsonIgnore
    private String biography;
    @JsonIgnore
    private int birthYear;
    @JsonIgnore
    private int deathYear;
}
