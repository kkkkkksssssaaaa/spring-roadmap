package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeAdvice 실행");

        long startTime = System.currentTimeMillis();

        // 얘만 호출하면 된다
        // 다른 부가적인 요소들(e.g. 타깃 정보)은 프록시 팩토리에서 이미 생성되어 MethodInvocation 에 포함되어 있다
        Object result = invocation.proceed();

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;

        log.info("TimeAdvice 종료 resultTime={}", resultTime);

        return result;
    }
}
