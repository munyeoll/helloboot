package tobyspring.helloboot;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import tobyspring.config.MySpringBootApplication;

@MySpringBootApplication
public class HellospringApplication {

    @Bean
    ApplicationRunner applicationRunner(Environment environment) {
        return args -> {
            String property = environment.getProperty("my.name");
            System.out.println("my.name: " + property);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(HellospringApplication.class, args);
    }

}
