package es.cesguiro.domain.service.impl;

import es.cesguiro.common.annotation.DomainService;
import es.cesguiro.common.exception.ResourceNotFoundException;
import es.cesguiro.domain.model.Genre;
import es.cesguiro.domain.repository.GenreRepository;
import es.cesguiro.domain.service.GenreService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public List<Genre> getByIdBook(long idBook) {
        return genreRepository.getByIdBook(idBook);
    }

    @Override
    public List<Genre> findAllById(List<Genre> genres) {
        List<Genre> foundGenres =  genreRepository.findAllById(
                genres
                        .stream()
                        .map(Genre::getId)
                        .toArray(Long[]::new)
        );
        if(foundGenres.size() != genres.size()) {
            throw new ResourceNotFoundException("Some genres were not found");
        }
        return foundGenres;
    }
}
