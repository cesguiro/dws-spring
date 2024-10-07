package es.cesguiro.domain.service.impl;

import es.cesguiro.common.annotations.DomainService;
import es.cesguiro.domain.exception.ResourceAlreadyExistsException;
import es.cesguiro.domain.model.command.GenreCommand;
import es.cesguiro.domain.model.query.GenreQuery;
import es.cesguiro.domain.repository.GenreRepository;
import es.cesguiro.domain.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@DomainService
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public void insert(GenreCommand genreCommand) {
        if(genreRepository.findBySlug(genreCommand.getSlug()).isPresent()) {
            throw new ResourceAlreadyExistsException("Slug " + genreCommand.getSlug() + " already exists");
        }
        genreRepository.insert(genreCommand);
    }
}
