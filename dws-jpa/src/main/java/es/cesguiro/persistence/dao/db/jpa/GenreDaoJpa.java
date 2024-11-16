package es.cesguiro.persistence.dao.db.jpa;

import es.cesguiro.domain.model.Genre;
import es.cesguiro.persistence.dao.db.GenreDaoDb;
import es.cesguiro.persistence.dao.db.jpa.entity.GenreEntity;
import es.cesguiro.persistence.dao.db.jpa.mapper.GenreJpaMapper;
import es.cesguiro.persistence.dao.db.jpa.repository.GenreJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GenreDaoJpa implements GenreDaoDb {

    private final GenreJpaRepository genreJpaRepository;

    @Override
    public List<Genre> getByIdBook(long idBook) {
        return genreJpaRepository
                .findByBooksId(idBook)
                .stream()
                .map(GenreJpaMapper.INSTANCE::toGenre)
                .toList();
    }

    @Override
    public List<Genre> findAllById(Long[] ids) {
        return genreJpaRepository
                .findAllById(List.of(ids))
                .stream()
                .map(GenreJpaMapper.INSTANCE::toGenre)
                .toList();
    }

    @Override
    public List<Genre> getByIsbnBook(String isbn) {
        return genreJpaRepository
                .findByBooksIsbn(isbn)
                .stream()
                .map(GenreJpaMapper.INSTANCE::toGenre)
                .toList();
    }

    @Override
    public List<Genre> getAll() {
        return genreJpaRepository
                .findAll()
                .stream()
                .map(GenreJpaMapper.INSTANCE::toGenre)
                .toList();
    }

    @Override
    public List<Genre> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return genreJpaRepository.findAll(pageable)
                .stream()
                .map(GenreJpaMapper.INSTANCE::toGenre)
                .toList();
    }

    @Override
    public Optional<Genre> findById(long id) {
        return genreJpaRepository.findById(id)
                .map(GenreJpaMapper.INSTANCE::toGenre);
    }

    @Override
    public long insert(Genre genre) {
        GenreEntity genreEntity = genreJpaRepository
                .save(GenreJpaMapper.INSTANCE.toGenreEntity(genre));
        return genreEntity.getId();
    }

    @Override
    public void update(Genre genre) {
        genreJpaRepository.save(GenreJpaMapper.INSTANCE.toGenreEntity(genre));
    }

    @Override
    public void delete(long id) {
        genreJpaRepository.deleteById(id);
    }

    @Override
    public int count() {
        return (int) genreJpaRepository.count();
    }
}
