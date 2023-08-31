package dev.kkkkkksssssaaaa.springroadmap.advancedaop.pointcut;

import dev.kkkkkksssssaaaa.springroadmap.advancedaop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class ExecutionTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void printMethod() {
        // java.lang.String dev.kkkkkksssssaaaa.springroadmap.advancedaop.member.MemberServiceImpl.hello(java.lang.String)
        log.info("helloMethod={}", helloMethod);
    }

    @Test
    void exactMatch() {
        pointcut.setExpression("execution(public java.lang.String dev.kkkkkksssssaaaa.springroadmap.advancedaop.member.MemberServiceImpl.hello(String))");

        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void allMatch() {
        pointcut.setExpression("execution(* *(..))");

        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void nameMatch1() {
        pointcut.setExpression("execution(* hello(..))");

        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void nameMatch2() {
        pointcut.setExpression("execution(* hel*(..))");

        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void nameMatch3() {
        pointcut.setExpression("execution(* *lo*(..))");

        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void nameMatchFailed() {
        pointcut.setExpression("execution(* nono(..))");

        assertFalse(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void packageMatch() {
        pointcut.setExpression("execution(public java.lang.String dev.kkkkkksssssaaaa.springroadmap.advancedaop.member.MemberServiceImpl.hello(String))");

        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void packageMatch2() {
        pointcut.setExpression("execution(public java.lang.String dev.kkkkkksssssaaaa.springroadmap.advancedaop.member.*.*(..))");

        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void packageMatch3() {
        pointcut.setExpression("execution(public java.lang.String dev.kkkkkksssssaaaa.springroadmap.advancedaop.member..*.*(..))");

        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void packageMatchFailed() {
        pointcut.setExpression("execution(public java.lang.String dev.kkkkkksssssaaaa.springroadmap.advancedaop.*.*(..))");

        assertFalse(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void typeExactMatch() {
        pointcut.setExpression("execution(public java.lang.String dev.kkkkkksssssaaaa.springroadmap.advancedaop.member.MemberServiceImpl.*(..))");

        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void typeMatchSuperType() {
        pointcut.setExpression("execution(public java.lang.String dev.kkkkkksssssaaaa.springroadmap.advancedaop.member.MemberService.*(..))");

        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void typeMatchInternal() throws NoSuchMethodException {
        pointcut.setExpression("execution(public java.lang.String dev.kkkkkksssssaaaa.springroadmap.advancedaop.member.MemberServiceImpl.*(..))");
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);

        assertTrue(pointcut.matches(internalMethod, MemberServiceImpl.class));
    }

    @Test
    void typeMatchInternalFailed() throws NoSuchMethodException {
        pointcut.setExpression("execution(public java.lang.String dev.kkkkkksssssaaaa.springroadmap.advancedaop.member.MemberService.*(..))");
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);

        assertFalse(pointcut.matches(internalMethod, MemberServiceImpl.class));
    }

    @Test
    void argsMatch() {
        pointcut.setExpression("execution(* *(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void noArgsMatch() {
        pointcut.setExpression("execution(* *())");
        assertFalse(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void oneArgsMatch() {
        pointcut.setExpression("execution(* *(*))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void allTypeArgsMatch() {
        pointcut.setExpression("execution(* *(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void allTypeArgsMatchIfStartString() {
        pointcut.setExpression("execution(* *(String, ..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }
}
