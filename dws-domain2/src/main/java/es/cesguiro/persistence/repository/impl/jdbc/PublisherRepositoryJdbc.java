package es.cesguiro.persistence.repository.impl.jdbc;

import es.cesguiro.domain.model.Publisher;
import es.cesguiro.domain.repository.PublisherRepository;
import es.cesguiro.persistence.repository.impl.jdbc.mapper.PublisherRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PublisherRepositoryJdbc implements PublisherRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Publisher> findById(Long id) {
        String sql = """
                SELECT * FROM publishers
                WHERE id = ?
                """;
        try
        {
            return Optional.of(jdbcTemplate.queryForObject(sql, new PublisherRowMapper(), id));
        }
        catch (Exception e)
        {
            return Optional.empty();
        }
    }
}
