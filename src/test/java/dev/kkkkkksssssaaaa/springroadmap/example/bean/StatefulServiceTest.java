package dev.kkkkkksssssaaaa.springroadmap.example.bean;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService service1 = context.getBean(StatefulService.class);
        StatefulService service2 = context.getBean(StatefulService.class);

        // given - 1. A 사용자가 10000원 주문
        service1.order("userA", 10000);

        // given - 2. B 사용자가 20000원 주문
        service2.order("userB", 20000);

        // when 주문 금액 조회
        int price = service1.getPrice();

        assertEquals(20000, price);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}