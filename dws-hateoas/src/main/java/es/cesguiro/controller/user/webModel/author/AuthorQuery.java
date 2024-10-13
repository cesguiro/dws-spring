package es.cesguiro.controller.user.webModel.author;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
