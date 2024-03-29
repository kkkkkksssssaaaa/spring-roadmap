package dev.kkkkkksssssaaaa.springroadmap.advancedaop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV2 {

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature());

        return joinPoint.proceed();
    }

    // 이러한 형태의 포인트컷 메서드를 포인트컷 시그니처라고 한다!
    @Pointcut("execution(* dev.kkkkkksssssaaaa.springroadmap.advancedaop.order..*(..))")
    private void allOrder() {}
}
