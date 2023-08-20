package dev.kkkkkksssssaaaa.springroadmap.advancedaop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV3 {

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature());

        return joinPoint.proceed();
    }

    @Around("allService() && allService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());

            Object result  = joinPoint.proceed();

            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());

            return result;
        } catch (Exception e) {
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());

            throw e;
        } finally {
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }

    // 이러한 형태의 포인트컷 메서드를 포인트컷 시그니처라고 한다!
    @Pointcut("execution(* dev.kkkkkksssssaaaa.springroadmap.advancedaop.order..*(..))")
    private void allOrder() {}

    @Pointcut("execution(* *..*Service.*(..))")
    private void allService() {}
}
