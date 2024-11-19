package es.cesguiro.persistence.dao.db.jdbc;

import es.cesguiro.domain.model.Book;
import es.cesguiro.domain.model.Publisher;
import es.cesguiro.persistence.dao.db.PublisherDaoDb;
import es.cesguiro.persistence.dao.db.jdbc.mapper.PublisherRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
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
    public List<Publisher> getAll(int page, int size) {
        return List.of();
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
    public int count() {
        return 0;
    }

    @Override
    public Publisher save(Publisher publisher) {
        return null;
    }

}
