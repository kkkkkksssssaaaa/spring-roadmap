package dev.kkkkkksssssaaaa.springroadmap.domain;

import dev.kkkkkksssssaaaa.springroadmap.AppConfig;
import dev.kkkkkksssssaaaa.springroadmap.domain.discount.DiscountPolicy;
import dev.kkkkkksssssaaaa.springroadmap.domain.discount.FixDiscountPolicy;
import dev.kkkkkksssssaaaa.springroadmap.domain.discount.RateDiscountPolicy;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemberRepository;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemberService;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemberServiceImpl;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemoryMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    AnnotationConfigApplicationContext context;

    @BeforeEach
    void beforeEach() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @Nested
    class FindBeanTest {

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

    @Nested
    class FindBeanByNameTest {

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
                () -> context.getBean("xxx", MemberServiceImpl.class)
            );
        }
    }

    @Nested
    class DuplicatedBeanTypeTest {

        @Test
        void 타입으로_조회시_같은_타입이_둘_이상_있으면_중복_오류가_발생한다() {
            ApplicationContext duplicateBeanApplicationContext =
                new AnnotationConfigApplicationContext(DuplicateBeanConfig.class);

            assertThrows(
                NoUniqueBeanDefinitionException.class,
                () -> duplicateBeanApplicationContext.getBean(MemberRepository.class)
            );
        }

        @Test
        void 타입으로_조회시_타입이_둘_이상_있으면_빈_이름을_명시한다() {
            ApplicationContext duplicateBeanApplicationContext =
                new AnnotationConfigApplicationContext(DuplicateBeanConfig.class);

            assertInstanceOf(
                MemberRepository.class,
                duplicateBeanApplicationContext.getBean(
                    "memberRepository1",
                    MemberRepository.class
                )
            );
        }
        
        @Test
        void 특정_타입을_모두_조회하기() {
            ApplicationContext duplicateBeanContext =
                new AnnotationConfigApplicationContext(DuplicateBeanConfig.class);

            Map<String, MemberRepository> beansOfType 
                = duplicateBeanContext.getBeansOfType(MemberRepository.class);

            for (String key : beansOfType.keySet()) {
                System.out.println("key = " + key);
                System.out.println("value = " + beansOfType.get(key));
            }

            System.out.println("beansOfType = " + beansOfType);

            assertEquals(2, beansOfType.size());
        }

        @Configuration
        static class DuplicateBeanConfig {

            @Bean
            public MemberRepository memberRepository1() {
                return new MemoryMemberRepository();
            }

            @Bean
            public MemberRepository memberRepository2() {
                return new MemoryMemberRepository();
            }
        }
    }

    @Nested
    class FindExtendsClassTest {

        @Test
        void 부모_타입으로_조회시_자식이_둘_이상_있으면_중복_오류가_발생한다() {
            ApplicationContext extendsBeanContext =
                new AnnotationConfigApplicationContext(ExtendsConfig.class);

            assertThrows(
                NoUniqueBeanDefinitionException.class,
                () -> extendsBeanContext.getBean(DiscountPolicy.class)
            );
        }

        @Test
        void 부모_타입으로_조회시_자식이_둘_이상_있으면_빈_이름을_지정하면_된다() {
            ApplicationContext extendsBeanContext =
                new AnnotationConfigApplicationContext(ExtendsConfig.class);

            DiscountPolicy rateDiscountPolicy =
                extendsBeanContext.getBean("rateDiscountPolicy", DiscountPolicy.class);

            assertInstanceOf(RateDiscountPolicy.class, rateDiscountPolicy);
        }

        @Test
        void 특정_하위_타입으로_조회() {
            ApplicationContext extendsBeanContext =
                new AnnotationConfigApplicationContext(ExtendsConfig.class);

            RateDiscountPolicy rateDiscountPolicy
                = extendsBeanContext.getBean("rateDiscountPolicy", RateDiscountPolicy.class);

            assertInstanceOf(RateDiscountPolicy.class, rateDiscountPolicy);
        }

        @Test
        void 부모_타입으로_모두_조회() {
            ApplicationContext extendsBeanContext =
                new AnnotationConfigApplicationContext(ExtendsConfig.class);

            Map<String, DiscountPolicy> beansOfType = extendsBeanContext.getBeansOfType(DiscountPolicy.class);

            assertEquals(2, beansOfType.size());
        }

        @Test
        void Object타입으로_모두_조회() {
            ApplicationContext extendsBeanContext =
                new AnnotationConfigApplicationContext(ExtendsConfig.class);

            Map<String, Object> beansOfType = extendsBeanContext.getBeansOfType(Object.class);

            for (String key : beansOfType.keySet()) {
                System.out.println("key = " + key);
            }
        }

        @Configuration
        static class ExtendsConfig {

            @Bean
            public DiscountPolicy rateDiscountPolicy() {
                return new RateDiscountPolicy();
            }

            @Bean
            public DiscountPolicy fixDiscountPolicy() {
                return new FixDiscountPolicy();
            }
        }
    }
}