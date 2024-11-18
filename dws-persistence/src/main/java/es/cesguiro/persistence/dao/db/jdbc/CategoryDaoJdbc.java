package es.cesguiro.persistence.dao.db.jdbc;

import es.cesguiro.domain.model.Book;
import es.cesguiro.domain.model.Category;
import es.cesguiro.persistence.dao.db.CategoryDaoDb;
import es.cesguiro.persistence.dao.db.jdbc.mapper.CategoryRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryDaoJdbc implements CategoryDaoDb {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Category> findById(long id) {
        String sql = """
                        SELECT * FROM categories
                        WHERE id = ?
                     """;
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql,new CategoryRowMapper(), id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Category> getAll() {
        //TODO: Implementar obtener todas las categorias
        return List.of();
    }

    @Override
    public List<Category> getAll(int page, int size) {
        //TODO: Implementar obtener todas las categorias paginadas
        return List.of();
    }

    @Override
    public long insert(Category category) {
        //TODO: Implementar insertar una categoria
        return 0;
    }

    @Override
    public void update(Category category) {
        //TODO: Implementar actualizar una categoria
    }

    @Override
    public void delete(long id) {
        //TODO: Implementar borrar una categoria
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public Category save(Category category) {
        //TODO: Implementar guardar una categoria
        return null;
    }
}
