package es.cesguiro.persistence.dao.db.impl.jdbc;

import es.cesguiro.domain.model.ListWithCount;
import es.cesguiro.domain.model.Publisher;
import es.cesguiro.persistence.dao.db.PublisherDaoDb;
import es.cesguiro.persistence.dao.db.impl.jdbc.mapper.PublisherRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
//@Primary
@RequiredArgsConstructor
public class PublisherDaoJdbc implements PublisherDaoDb {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Publisher> findById(long id) {
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


    @Override
    public List<Publisher> getAll() {
        return List.of();
    }

    @Override
    public ListWithCount<Publisher> getAll(int page, int size) {
        return new ListWithCount<>(List.of(), 0);
    }

    @Override
    public long insert(Publisher publisher) {
        return 0;
    }

    @Override
    public void update(Publisher publisher) {

    }

    @Override
    public void delete(long id) {
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Publisher save(Publisher publisher) {
        publisher.setId(this.insert(publisher));
        return publisher;
    }
}
