package dev.kkkkkksssssaaaa.springroadmap.domain;

import dev.kkkkkksssssaaaa.springroadmap.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    void 모든_빈_출력하기() {
        String[] beanDefinitionNames = context.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = context.getBean(beanDefinitionName);
            System.out.println("been = " + bean);
        }
    }

    @Test
    void 애플리케이션_빈_출력하기() {
        String[] beanDefinitionNames = context.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = context.getBeanDefinition(beanDefinitionName);

            /**
             * BeanDefinition.ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
             * BeanDefinition.ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
             * */
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = context.getBean(beanDefinitionName);

                System.out.println("bean = " + bean);
            }
        }
    }
}