package dev.kkkkkksssssaaaa.springroadmap.basic.example.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.junit.jupiter.api.Assertions.assertNotSame;

public class PrototypeBeanTest {

    @Test
    void find() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find bean1");
        PrototypeBean bean1 = context.getBean(PrototypeBean.class);

        System.out.println("find bean2");
        PrototypeBean bean2 = context.getBean(PrototypeBean.class);

        assertNotSame(bean1, bean2);

        context.close();
    }

    @Scope("prototype")
    static class PrototypeBean {

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
