package es.cesguiro.persistence.dao.cache;

import java.util.Optional;

public interface GenericDaoCache<T> {
    void save(T t);
    void clearCache();
}
