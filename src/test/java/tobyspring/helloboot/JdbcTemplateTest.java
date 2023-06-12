package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
public class JdbcTemplateTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void init() {
        jdbcTemplate.execute("create table if not exists hello(name varchar(10) primary key, count int)");
    }

    @Test
    void insertAndQuery() {
        String query = "insert into hello values(?, ?)";
        jdbcTemplate.update(query, "Hello", 3);
        jdbcTemplate.update(query, "Boot", 10);

        Long count = jdbcTemplate.queryForObject("select count(1) from hello", Long.class);
        Assertions.assertThat(count).isEqualTo(2);
    }
}
