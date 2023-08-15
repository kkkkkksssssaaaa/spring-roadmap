package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.proxy.v6;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.TraceStatus;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.logtarce.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
// 포인트컷과 어드바이저를 손쉽게 만들어 주는 녀석이라고 생각하자
@Aspect
public class LogTraceAspect {

    private final LogTrace trace;

    @Autowired
    public LogTraceAspect(LogTrace trace) {
        this.trace = trace;
    }

    // Around 를 선언한 메서드 몸체가 어드바이스가 된다!
    @Around("execution(* dev.kkkkkksssssaaaa.springroadmap.advancedproxy.app..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        TraceStatus status = null;

        try {
            String message = joinPoint.getSignature().toShortString();

            status = trace.begin(message);

            Object result = joinPoint.proceed();

            trace.end(status);

            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
