package dev.kkkkkksssssaaaa.springroadmap.example.bean;

import dev.kkkkkksssssaaaa.springroadmap.AppConfig;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class SingletonTest {

    @Test
    void 싱글톤_패턴을_적용한_객체_사용() {
        SingletonService service1 = SingletonService.getInstance();
        SingletonService service2 = SingletonService.getInstance();

        assertEquals(service1, service2);
        assertSame(service1, service2);
    }

    @Test
    void 스프링_컨테이너와_싱글톤() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService service1 = context.getBean("memberService", MemberService.class);
        MemberService service2 = context.getBean("memberService", MemberService.class);

        System.out.println("service1 = " + service1);
        System.out.println("service2 = " + service2);

        assertEquals(service1, service2);
        assertSame(service1, service2);
    }
}
