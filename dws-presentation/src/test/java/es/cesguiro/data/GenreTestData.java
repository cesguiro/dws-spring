package es.cesguiro.data;

import es.cesguiro.domain.user.model.Genre;

import java.util.List;

public class GenreTestData {

    public static List<Genre> getGenres() {
        return List.of(
                new Genre(1, "Genre 1", "genre-1"),
                new Genre(2, "Genre 2", "genre-2"),
                new Genre(3, "Genre 3", "genre-3"),
                new Genre(4, "Genre 4", "genre-4")
        );
    }
}
