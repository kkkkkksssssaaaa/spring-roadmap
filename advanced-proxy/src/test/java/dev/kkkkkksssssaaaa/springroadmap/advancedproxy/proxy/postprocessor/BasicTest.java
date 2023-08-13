package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BasicTest {

    @Test
    void basicConfig() {
        ApplicationContext context = new AnnotationConfigApplicationContext(BasicConfig.class);

        A a = context.getBean("beanA", A.class);
        a.helloA();

        assertThrows(
            NoSuchBeanDefinitionException.class,
            () -> context.getBean("beanB", B.class)
        );
    }

    @Slf4j
    @Configuration
    static class BasicConfig {

        @Bean(name = "beanA")
        public A a() {
            return new A();
        }
    }

    @Slf4j
    static class A {

        public void helloA() {
            log.info("hello A");
        }
    }

    @Slf4j
    static class B {

        public void helloB() {
            log.info("hello B");
        }
    }
}
