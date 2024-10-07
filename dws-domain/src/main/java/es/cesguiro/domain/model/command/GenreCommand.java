package es.cesguiro.domain.model.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreCommand {

    private long id;
    private String name_es;
    private String name_en;
    private String slug;
}
