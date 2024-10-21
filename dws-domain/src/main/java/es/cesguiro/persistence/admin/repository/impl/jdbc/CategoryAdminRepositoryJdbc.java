package es.cesguiro.persistence.admin.repository.impl.jdbc;

import es.cesguiro.domain.admin.model.Category;
import es.cesguiro.domain.admin.repository.CategoryAdminRepository;
import es.cesguiro.persistence.admin.repository.impl.jdbc.mapper.CategoryRowMapper;
import es.cesguiro.persistence.admin.repository.impl.jdbc.mapper.PublisherRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryAdminRepositoryJdbc implements CategoryAdminRepository {

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
