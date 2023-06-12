package tobyspring.helloboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class HelloRepositoryTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    HelloRepository helloRepository;

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
