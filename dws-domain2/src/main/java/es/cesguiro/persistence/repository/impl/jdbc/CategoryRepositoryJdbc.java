package es.cesguiro.persistence.repository.impl.jdbc;

import es.cesguiro.domain.model.Category;
import es.cesguiro.domain.repository.CategoryRepository;
import es.cesguiro.persistence.repository.impl.jdbc.mapper.CategoryRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryJdbc implements CategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Category> findById(Long id) {
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
}
