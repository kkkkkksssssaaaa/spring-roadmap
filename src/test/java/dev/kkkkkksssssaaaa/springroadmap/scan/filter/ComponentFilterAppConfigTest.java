package dev.kkkkkksssssaaaa.springroadmap.scan.filter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        BeanA beanA = context.getBean("beanA", BeanA.class);

        assertNotNull(beanA);

        assertThrows(
            NoSuchBeanDefinitionException.class,
            () -> context.getBean("beanB", BeanB.class)
        );
    }

    @Configuration
    @ComponentScan(
        includeFilters = @Filter(
            type = FilterType.ANNOTATION,
            classes = {IncludeComponent.class}
        ),
        excludeFilters = @Filter(
            type = FilterType.ANNOTATION,
            classes = {ExcludeComponent.class}
        )
    )
    static class ComponentFilterAppConfig {

    }
}
