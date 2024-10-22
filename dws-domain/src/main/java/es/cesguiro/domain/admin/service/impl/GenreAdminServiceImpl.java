package es.cesguiro.domain.admin.service.impl;

import es.cesguiro.common.annotation.DomainService;
import es.cesguiro.common.exception.ResourceNotFoundException;
import es.cesguiro.domain.admin.model.Genre;
import es.cesguiro.domain.admin.repository.GenreAdminRepository;
import es.cesguiro.domain.admin.service.GenreAdminService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class GenreAdminServiceImpl implements GenreAdminService {

    private final GenreAdminRepository genreAdminRepository;

    @Override
    public List<Genre> getByIdBook(long idBook) {
        return genreAdminRepository.getByIdBook(idBook);
    }

    @Override
    public List<Genre> findAllById(List<Genre> genres) {
        List<Genre> foundGenres =  genreAdminRepository.findAllById(
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
