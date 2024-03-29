package dev.kkkkkksssssaaaa.springroadmap.basic.example.configuration;

import dev.kkkkkksssssaaaa.springroadmap.basic.AutoAppConfig;
import dev.kkkkkksssssaaaa.springroadmap.basic.domain.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService bean = context.getBean(MemberService.class);

        assertInstanceOf(MemberService.class, bean);
    }
}
