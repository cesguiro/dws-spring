package es.cesguiro.domain.admin.usecase.book;

import es.cesguiro.domain.admin.model.Genre;

import java.util.List;

public interface InsertGenresAdminUseCase {

    void execute(int id, List<Genre> genres);
}
