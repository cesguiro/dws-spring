package es.cesguiro.domain.usecase.book.admin;

import es.cesguiro.domain.model.Genre;

import java.util.List;

public interface BookInsertGenresUseCase {
    void execute(Integer id, List<Genre> genres);
}
