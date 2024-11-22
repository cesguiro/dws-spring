package es.cesguiro.persistence.repository.impl;

import es.cesguiro.domain.model.Author;
import es.cesguiro.domain.repository.AuthorRepository;
import es.cesguiro.persistence.dao.db.AuthorDaoDb;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepository {

    //@Qualifier("authorDaoJdbc")
    private final AuthorDaoDb authorDaoDb;

    /*
    Problema: Lombok no puede usar @Qualifier en el atributo authorDao.
    Solución: Quitar @RequiredArgsConstructor y crear un constructor con el atributo authorDao.
    Solución alternativa: Quitar @Qualifier en el atributo authorDao y usar @Primary en AuthorDaoJdbc.
     */
    AuthorRepositoryImpl(@Qualifier("authorDaoJdbc") AuthorDaoDb authorDaoDb) {
        this.authorDaoDb = authorDaoDb;
    }

    @Override
    public List<Author> getByIsbnBook(String isbn) {
        return authorDaoDb.getByIsbnBook(isbn);
    }

    @Override
    public List<Author> getByIdBook(long idBook) {
        return authorDaoDb.getByIdBook(idBook);
    }

    @Override
    public List<Author> findAllById(Long[] ids) {
        return authorDaoDb.findAllById(ids);
    }
}
