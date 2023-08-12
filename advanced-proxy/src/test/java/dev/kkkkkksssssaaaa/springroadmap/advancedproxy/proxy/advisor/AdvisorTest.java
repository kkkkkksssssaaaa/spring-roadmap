package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.advisor;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.common.advice.TimeAdvice;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.common.service.ServiceImpl;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

import java.lang.reflect.Method;

@Slf4j
class AdvisorTest {

    @Test
    void test1() {
        ServiceInterface target = new ServiceImpl();

        ProxyFactory factory = new ProxyFactory(target);

        // 가장 기본적인 어드바이저
        DefaultPointcutAdvisor advisor =
            new DefaultPointcutAdvisor(
                // 이 옵션을 주면 어느 대상이던간에 어드바이스를 적용한다는 뜻이 된다. == filter: *
                // 참고로 Advice 만 받는 생성자에서도 내부적으로는 Pointcut.TRUE 를 기본적으로 할당해주고 있다.
                Pointcut.TRUE,
                new TimeAdvice()
            );

        factory.addAdvisor(advisor);

        ServiceInterface proxy = (ServiceInterface) factory.getProxy();

        proxy.save();
        proxy.find();
    }

    @Test
    void test2() {
        ServiceInterface target = new ServiceImpl();

        ProxyFactory factory = new ProxyFactory(target);

        DefaultPointcutAdvisor advisor =
            new DefaultPointcutAdvisor(
                new MyPointcut(),
                new TimeAdvice()
            );

        factory.addAdvisor(advisor);

        ServiceInterface proxy = (ServiceInterface) factory.getProxy();

        proxy.save();
        proxy.find();
    }

    @Test
    void test3() {
        ServiceInterface target = new ServiceImpl();

        ProxyFactory factory = new ProxyFactory(target);

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("save");

        DefaultPointcutAdvisor advisor =
            new DefaultPointcutAdvisor(
                pointcut,
                new TimeAdvice()
            );

        factory.addAdvisor(advisor);

        ServiceInterface proxy = (ServiceInterface) factory.getProxy();

        proxy.save();
        proxy.find();
    }

    static class MyPointcut implements Pointcut {

        @Override
        public ClassFilter getClassFilter() {
            return ClassFilter.TRUE;
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            return new MyMethodMatcher();
        }
    }

    static class MyMethodMatcher implements MethodMatcher {

        private final String matchName = "save";

        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            log.info("포인트컷 호출 method={} targetClass={}", method, targetClass);
            log.info("포인트컷 결과 result={}", method.getName().equals(matchName));

            return method.getName().equals(matchName);
        }

        // true 를 반환하는 경우 아래의 ...args 를 받는 로직이 수행된다
        // 단, 동적으로 수행되는 정보들이기 때문에 캐싱을 할 수가 없어 성능에 영향이 끼칠 수 있다는 단점이 있다
        @Override
        public boolean isRuntime() {
            return false;
        }

        @Override
        public boolean matches(Method method, Class<?> targetClass, Object... args) {
            return false;
        }
    }
}
