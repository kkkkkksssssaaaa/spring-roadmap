package dev.kkkkkksssssaaaa.springroadmap.core;

import dev.kkkkkksssssaaaa.springroadmap.AppConfig;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemberRepository;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemberServiceImpl;
import dev.kkkkkksssssaaaa.springroadmap.domain.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ConfigurationSingletonTest {

    @Test
    void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = context.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = context.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = context.getBean("memberRepository", MemberRepository.class);

        assertEquals(memberService.getRepository(), orderService.getRepository());
        assertSame(memberService.getRepository(), orderService.getRepository());
        assertSame(memberService.getRepository(), memberRepository);
        assertSame(orderService.getRepository(), memberRepository);
    }
}
