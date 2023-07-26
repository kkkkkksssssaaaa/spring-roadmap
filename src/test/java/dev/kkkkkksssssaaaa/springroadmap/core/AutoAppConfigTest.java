package dev.kkkkkksssssaaaa.springroadmap.core;

import dev.kkkkkksssssaaaa.springroadmap.AutoAppConfig;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemberService;
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
