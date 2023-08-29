package dev.kkkkkksssssaaaa.springroadmap.advancedaop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class AspectV6Advice {

    @Before("dev.kkkkkksssssaaaa.springroadmap.advancedaop.order.aop.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[before] {}", joinPoint.getSignature());
    }

    @AfterReturning(
        value = "dev.kkkkkksssssaaaa.springroadmap.advancedaop.order.aop.Pointcuts.orderAndService()",
        returning = "result"
    )
    public void doReturn(JoinPoint joinPoint, Object result) {
        log.info("[return] {} return={}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(
        value = "dev.kkkkkksssssaaaa.springroadmap.advancedaop.order.aop.Pointcuts.orderAndService()",
        throwing = "ex"
    )
    public void doThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[ex] {} message={}", ex);
    }

    @After("dev.kkkkkksssssaaaa.springroadmap.advancedaop.order.aop.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[after] {}", joinPoint.getSignature());
    }
}
