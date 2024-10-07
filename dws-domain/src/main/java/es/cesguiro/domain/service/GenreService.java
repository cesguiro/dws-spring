package es.cesguiro.domain.service;

import es.cesguiro.domain.model.command.GenreCommand;

public interface GenreService {

    void insert(GenreCommand genreCommand);
}
