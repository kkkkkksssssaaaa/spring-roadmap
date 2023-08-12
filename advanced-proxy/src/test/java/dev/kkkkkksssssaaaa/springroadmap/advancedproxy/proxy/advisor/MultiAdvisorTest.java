package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.advisor;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.common.service.ServiceImpl;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

class MultiAdvisorTest {

    @Test
    void test1() {
        // client -> proxy2(advisor2) -> proxy1(advisor1) -> target
        ServiceInterface target = new ServiceImpl();

        ProxyFactory factory1 = new ProxyFactory(target);

        DefaultPointcutAdvisor advisor1 =
            new DefaultPointcutAdvisor(
                Pointcut.TRUE,
                new Advice1()
            );

        factory1.addAdvisor(advisor1);

        ServiceInterface proxy1 = (ServiceInterface) factory1.getProxy();

        ProxyFactory factory2 = new ProxyFactory(proxy1);

        DefaultPointcutAdvisor advisor2 =
            new DefaultPointcutAdvisor(
                Pointcut.TRUE,
                new Advice2()
            );

        factory2.addAdvisor(advisor2);

        ServiceInterface proxy2 = (ServiceInterface) factory2.getProxy();

        proxy2.save();
        proxy2.find();
    }

    @Test
    void test2() {
        ServiceInterface target = new ServiceImpl();

        ProxyFactory factory = new ProxyFactory(target);

        DefaultPointcutAdvisor advisor1 =
            new DefaultPointcutAdvisor(
                Pointcut.TRUE,
                new Advice1()
            );

        DefaultPointcutAdvisor advisor2 =
            new DefaultPointcutAdvisor(
                Pointcut.TRUE,
                new Advice2()
            );

        // 추가한 순서대로 동작한다.
        factory.addAdvisor(advisor2);
        factory.addAdvisor(advisor1);

        ServiceInterface proxy = (ServiceInterface) factory.getProxy();

        proxy.save();
        proxy.find();
    }

    @Slf4j
    static class Advice1 implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advice1 호출");
            return invocation.proceed();
        }
    }

    @Slf4j
    static class Advice2 implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advice2 호출");
            return invocation.proceed();
        }
    }
}
