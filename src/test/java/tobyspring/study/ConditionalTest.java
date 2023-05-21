package tobyspring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

public class ConditionalTest {

    @Test
    void conditional() {
        new ApplicationContextRunner().withUserConfiguration(Config1.class).run(context -> {
            Assertions.assertThat(context).hasSingleBean(Config1.class);
            Assertions.assertThat(context).hasSingleBean(MyBean.class);
        });
        new ApplicationContextRunner().withUserConfiguration(Config2.class).run(context -> {
            Assertions.assertThat(context).doesNotHaveBean(Config2.class);
            Assertions.assertThat(context).doesNotHaveBean(MyBean.class);
        });
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BoolanCondition.class)
    @interface BooleanConditional {
        boolean value();
    }

    @Configuration
    @BooleanConditional(true)
    static class Config1 {
        @Bean
        MyBean bean() {
            return new MyBean();
        }
    }

    @Configuration
    @BooleanConditional(false)
    static class Config2 {
        @Bean
        MyBean bean() {
            return new MyBean();
        }
    }


    static class MyBean {
    }

    static class BoolanCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(BooleanConditional.class.getName());
            Boolean value = (Boolean) annotationAttributes.get("value");
            return value;
        }
    }
}
