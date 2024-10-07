package es.cesguiro.domain.service.impl;

import es.cesguiro.domain.model.command.GenreCommand;
import es.cesguiro.domain.repository.GenreRepository;
import es.cesguiro.domain.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public void insert(GenreCommand genreCommand) {
        genreRepository.insert(genreCommand);
    }
}
