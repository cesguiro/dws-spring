package es.cesguiro.controller.user.webModel.publisher;

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
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class PublisherQuery extends RepresentationModel<PublisherQuery> {

    //private Long id;
    private String name;
    @JsonIgnore
    private String slug;
}
