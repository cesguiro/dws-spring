package es.cesguiro.data;

import es.cesguiro.domain.user.model.Author;

import java.util.List;

public class AuthorTestData {

    public static List<Author> getAuthors() {
        return List.of(
                new Author(1, "Author 1", "spain", "biography author 1", 1900, 1970),
                new Author(2, "Author 2", "france", "biography author 2", 1910, 1980),
                new Author(3, "Author 3", "usa", "biography author 3", 1920, 1990)
        );
    }
}
