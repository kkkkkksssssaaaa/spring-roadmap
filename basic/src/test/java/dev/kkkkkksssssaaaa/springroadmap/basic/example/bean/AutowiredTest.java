package dev.kkkkkksssssaaaa.springroadmap.basic.example.bean;

import dev.kkkkkksssssaaaa.springroadmap.basic.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void autowiredOption() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean {

        @Autowired(required = false)
        public void setNoBean1(Member nonRequiredBean) {
            System.out.println("nonRequiredBean = " + nonRequiredBean);
        }

        @Autowired
        public void setNoBean2(@Nullable Member nullableBean) {
            System.out.println("nullableBean = " + nullableBean);
        }

        @Autowired
        public void setNoBean3(Optional<Member> optionalBean) {
            System.out.println("optionalBean = " + optionalBean);
        }
    }
}
