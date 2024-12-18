package es.cesguiro.persistence.dao.db.impl.jpa;

import es.cesguiro.domain.model.Author;
import es.cesguiro.domain.model.ListWithCount;
import es.cesguiro.persistence.dao.db.AuthorDaoDb;
import es.cesguiro.persistence.dao.db.impl.jpa.entity.AuthorEntity;
import es.cesguiro.persistence.dao.db.impl.jpa.mapper.AuthorJpaMapper;
import es.cesguiro.persistence.dao.db.impl.jpa.repository.AuthorJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("authorDaoJpa")
@Primary
@RequiredArgsConstructor
public class AuthorDaoJpa implements AuthorDaoDb {

    private final AuthorJpaRepository authorJpaRepository;

    @Override
    public List<Author> getByIsbnBook(String isbn) {
        return authorJpaRepository
                .findByBooksIsbn(isbn)
                .stream()
                .map(AuthorJpaMapper.INSTANCE::toAuthor)
                .toList();
    }

    @Override
    public List<Author> getByIdBook(long idBook) {
        return authorJpaRepository
                .findByBooksId(idBook)
                .stream()
                .map(AuthorJpaMapper.INSTANCE::toAuthor)
                .toList();
    }

    @Override
    public List<Author> findAllById(Long[] ids) {
        return authorJpaRepository
                .findAllById(List.of(ids))
                .stream()
                .map(AuthorJpaMapper.INSTANCE::toAuthor)
                .toList();
    }

    @Override
    public List<Author> getAll() {
        return authorJpaRepository
                .findAll()
                .stream()
                .map(AuthorJpaMapper.INSTANCE::toAuthor)
                .toList();
    }

    @Override
    public ListWithCount<Author> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AuthorEntity> authorEntities = authorJpaRepository.findAll(pageable);
        return new ListWithCount<>(
                authorEntities.getContent().stream()
                        .map(AuthorJpaMapper.INSTANCE::toAuthor)
                        .toList(),
                authorEntities.getNumberOfElements()
        );
    }

    @Override
    public Optional<Author> findById(long id) {
        return authorJpaRepository.findById(id)
                .map(AuthorJpaMapper.INSTANCE::toAuthor);
    }

    @Override
    public long insert(Author author) {
        AuthorEntity authorEntity = authorJpaRepository
                .save(AuthorJpaMapper.INSTANCE.toAuthorEntity(author));
        return authorEntity.getId();
    }

    @Override
    public void update(Author author) {
        authorJpaRepository.save(AuthorJpaMapper.INSTANCE.toAuthorEntity(author));
    }

    @Override
    public void delete(long id) {
        authorJpaRepository.deleteById(id);
    }

    @Override
    public long count() {
        return authorJpaRepository.count();
    }

    @Override
    public Author save(Author author) {
        return AuthorJpaMapper.INSTANCE.toAuthor(
                authorJpaRepository.save(AuthorJpaMapper.INSTANCE.toAuthorEntity(author))
        );
    }
}
