package tobyspring.helloboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class HellospringApplication {
    private final JdbcTemplate jdbcTemplate;

    public HellospringApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    void init() {
        jdbcTemplate.execute("create table if not exists hello(name varchar(10) primary key, count int)");
    }

    public static void main(String[] args) {
        SpringApplication.run(HellospringApplication.class, args);
    }

}
