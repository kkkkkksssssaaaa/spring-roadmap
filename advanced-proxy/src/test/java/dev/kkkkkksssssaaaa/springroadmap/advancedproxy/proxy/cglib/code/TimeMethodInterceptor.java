package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.cglib.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor {

    private final Object target;

    public TimeMethodInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(
        Object obj,
        Method method,
        Object[] args,
        MethodProxy proxy
    ) throws Throwable {
        log.info("TimeProxy 실행");

        long startTime = System.currentTimeMillis();

        // Method 보다는 MethodProxy 를 사용하는 것이 조금 더 빠르다고 한다
        // CGLib 공식 문서에서 권장한다고 한다
        Object result = proxy.invoke(target, args);

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;

        log.info("TimeProxy 종료 resultTime={}", resultTime);

        return result;
    }
}
