package es.cesguiro.domain.usecase.book.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUser {

    private String name;
    private String slug;
}
