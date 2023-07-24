package dev.kkkkkksssssaaaa.springroadmap.domain;

import dev.kkkkkksssssaaaa.springroadmap.AppConfig;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemberService;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    AnnotationConfigApplicationContext context;

    @BeforeEach
    void beforeEach() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
    }

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

    @Test
    void 빈_이름으로_조회() {
        MemberService memberService = context.getBean("memberService", MemberService.class);

        assertInstanceOf(MemberServiceImpl.class, memberService);
    }

    @Test
    void 이름_없이_타입으로만_조회() {
        MemberService memberService = context.getBean(MemberService.class);

        assertInstanceOf(MemberServiceImpl.class, memberService);
    }

    @Test
    void 구체_타입으로_조회() {
        MemberService memberService = context.getBean("memberService", MemberServiceImpl.class);

        assertInstanceOf(MemberServiceImpl.class, memberService);
    }

    @Test
    void 빈_이름으로_조회_불가() {
        assertThrows(
            NoSuchBeanDefinitionException.class,
            () -> context.getBean("xxx", MemberServiceImpl.class));
    }
}