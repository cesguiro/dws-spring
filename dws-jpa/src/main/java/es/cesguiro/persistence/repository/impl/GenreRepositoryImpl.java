package es.cesguiro.persistence.repository.impl;

import es.cesguiro.domain.model.Genre;
import es.cesguiro.domain.repository.GenreRepository;
import es.cesguiro.persistence.dao.db.GenreDaoDb;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryImpl implements GenreRepository {

    private final GenreDaoDb genreDaoDb;

    @Override
    public List<Genre> getByIsbnBook(String isbn) {
        return genreDaoDb.getByIsbnBook(isbn);
    }

    @Override
    public List<Genre> getByIdBook(long idBook) {
        return genreDaoDb.getByIdBook(idBook);
    }

    @Override
    public List<Genre> findAllById(Long[] ids) {
        return genreDaoDb.findAllById(ids);
    }
}
