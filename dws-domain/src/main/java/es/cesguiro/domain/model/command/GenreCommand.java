package es.cesguiro.domain.model.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreCommand {

    private long id;
    private String nameEs;
    private String nameEn;
    private String slug;
}
