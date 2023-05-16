package tobyspring.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hello")
@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping
//    @ResponseBody @RestController 에서 @ResponseBody 를 메타 애노테이션으로 가지고 있음
    public String hello(String name) {
        if( name == null || name.trim().length() == 0 ) throw new IllegalArgumentException();
        return helloService.sayHello(name);
    }

}
