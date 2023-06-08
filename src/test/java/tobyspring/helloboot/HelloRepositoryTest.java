package tobyspring.helloboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@HellospringTest
public class HelloRepositoryTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    HelloRepository helloRepository;

    @BeforeEach
    void init() {
        jdbcTemplate.execute("create table if not exists hello(name varchar(10) primary key, count int)");
    }

    @Test
    void findHelloFailed() {
        assertThat(helloRepository.findHello("Hello")).isNull();
    }

    @Test
    void increaseTest() {
        helloRepository.increaseCount("Hello");
        assertThat(helloRepository.countOf("Hello")).isEqualTo(1);

        helloRepository.increaseCount("Hello");
        assertThat(helloRepository.countOf("Hello")).isEqualTo(2);
    }
}
