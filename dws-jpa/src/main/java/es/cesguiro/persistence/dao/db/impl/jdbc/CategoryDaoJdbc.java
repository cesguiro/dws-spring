package es.cesguiro.persistence.dao.db.impl.jdbc;

import es.cesguiro.domain.model.Category;
import es.cesguiro.domain.model.ListWithCount;
import es.cesguiro.persistence.dao.db.CategoryDaoDb;
import es.cesguiro.persistence.dao.db.impl.jdbc.mapper.CategoryRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
//@Primary
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
    public ListWithCount<Category> getAll(int page, int size) {
        //TODO: Implementar obtener todas las categorias paginadas
        return new ListWithCount<>(List.of(), 0);
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
    public long count() {
        return 0;
    }

    @Override
    public Category save(Category category) {
        category.setId(this.insert(category));
        return category;
    }
}
