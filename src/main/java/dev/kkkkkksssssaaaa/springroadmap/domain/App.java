package dev.kkkkkksssssaaaa.springroadmap.domain;

import dev.kkkkkksssssaaaa.springroadmap.AppConfig;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        // AnnotationConfigApplicationContext 이란? Configuration 어노테이션을 사용하여 ApplicationContext 를 초기화해주는 녀석.
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean("memberService", MemberService.class);
    }
}
