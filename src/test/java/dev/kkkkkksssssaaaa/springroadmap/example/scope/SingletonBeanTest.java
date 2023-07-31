package dev.kkkkkksssssaaaa.springroadmap.example.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.junit.jupiter.api.Assertions.assertSame;

public class SingletonBeanTest {

    @Test
    void find() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean bean1 = context.getBean(SingletonBean.class);
        SingletonBean bean2 = context.getBean(SingletonBean.class);

        assertSame(bean1, bean2);

        context.close();
    }

    @Scope("singleton")
    static class SingletonBean {

        @PostConstruct
        public void init() {
            System.out.println("init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("destroy");
        }
    }
}
