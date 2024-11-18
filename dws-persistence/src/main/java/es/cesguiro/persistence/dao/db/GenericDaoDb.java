package es.cesguiro.persistence.dao.db;

import es.cesguiro.domain.model.Book;

import java.util.List;
import java.util.Optional;

public interface GenericDaoDb<T> {

    List<T> getAll();
    List<T> getAll(int page, int size);
    Optional<T> findById(long id);
    long insert(T t);
    void update(T t);
    void delete(long id);
    int count();
    T save(T t);
}
