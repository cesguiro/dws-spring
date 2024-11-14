package es.cesguiro.persistence.repository.impl;

import es.cesguiro.domain.model.Author;
import es.cesguiro.domain.repository.AuthorRepository;
import es.cesguiro.persistence.dao.db.AuthorDaoDb;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepository {

    private final AuthorDaoDb authorDao;

    @Override
    public List<Author> getByIsbnBook(String isbn) {
        return authorDao.getByIsbnBook(isbn);
    }

    @Override
    public List<Author> getByIdBook(long idBook) {
        return authorDao.getByIdBook(idBook);
    }

    @Override
    public List<Author> findAllById(Long[] ids) {
        return authorDao.findAllById(ids);
    }
}
