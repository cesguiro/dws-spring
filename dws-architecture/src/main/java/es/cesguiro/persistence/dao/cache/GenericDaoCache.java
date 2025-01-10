package es.cesguiro.persistence.dao.cache;

public interface GenericDaoCache<T> {
    void save(T t);
    void clearCache();
}
