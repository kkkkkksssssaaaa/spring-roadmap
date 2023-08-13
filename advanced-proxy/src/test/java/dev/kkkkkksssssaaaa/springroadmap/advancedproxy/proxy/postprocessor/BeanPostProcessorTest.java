package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BeanPostProcessorTest {

    @Test
    void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(BasicConfig.class);

        B b = context.getBean("beanA", B.class);
        b.helloB();

        assertThrows(
            NoSuchBeanDefinitionException.class,
            () -> context.getBean("beanB", A.class)
        );
    }

    @Slf4j
    @Configuration
    static class BasicConfig {

        @Bean(name = "beanA")
        public A a() {
            return new A();
        }

        @Bean
        public AToBPostProcessor helloPostProcessor() {
            return new AToBPostProcessor();
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

    @Slf4j
    static class AToBPostProcessor implements BeanPostProcessor {

        // 빈 초기화 전에 수행된다
        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
        }

        // 빈 초기화 이후에 수행된다
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            log.info("beanName={} bean={}", beanName, bean);

            if (bean instanceof A) {
                return new B();
            }

            return bean;
        }
    }
}
