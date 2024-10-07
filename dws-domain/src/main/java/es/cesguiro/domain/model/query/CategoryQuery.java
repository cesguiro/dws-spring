package es.cesguiro.domain.model.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryQuery {

    private long id;
    private String name;
    private String slug;
}
