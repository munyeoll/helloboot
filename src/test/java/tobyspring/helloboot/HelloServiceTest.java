package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {

    @Test
    void simpleHelloService() {
        HelloService simpleHelloService = new SimpleHelloService();
        String ret = simpleHelloService.sayHello("Spring");
        Assertions.assertThat(ret).isEqualTo("Hello Spring");
    }

    @Test
    void helloDecoratorService() {
        HelloDecorator helloDecorator = new HelloDecorator(name -> name);
        String ret = helloDecorator.sayHello("Test");
        Assertions.assertThat(ret).isEqualTo("*Test*");
    }
}
