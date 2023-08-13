package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.proxy.v3;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.logtarce.LogTrace;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.app.v1.OrderControllerV1;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.app.v1.OrderControllerV1Impl;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.app.v1.OrderRepositoryV1;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.app.v1.OrderRepositoryV1Impl;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.app.v1.OrderServiceV1;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.app.v1.OrderServiceV1Impl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
//@Configuration
public class ProxyFactoryConfigV1 {

//    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace trace) {
        OrderControllerV1 target = new OrderControllerV1Impl(orderServiceV1(trace));

        ProxyFactory factory = new ProxyFactory(target);
        factory.addAdvisor(getAdvisor(trace));

        OrderControllerV1 proxy = (OrderControllerV1) factory.getProxy();

        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), trace.getClass());

        return proxy;
    }

//    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace trace) {
        OrderServiceV1 target = new OrderServiceV1Impl(orderRepositoryV1(trace));

        ProxyFactory factory = new ProxyFactory(target);
        factory.addAdvisor(getAdvisor(trace));

        OrderServiceV1 proxy = (OrderServiceV1) factory.getProxy();

        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), trace.getClass());

        return proxy;
    }

//    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace trace) {
        OrderRepositoryV1 target = new OrderRepositoryV1Impl();

        ProxyFactory factory = new ProxyFactory(target);
        factory.addAdvisor(getAdvisor(trace));

        OrderRepositoryV1 proxy = (OrderRepositoryV1) factory.getProxy();

        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), target.getClass());

        return proxy;
    }

    private Advisor getAdvisor(LogTrace trace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();

        pointcut.setMappedNames("request*", "order*", "save*");

        LogTraceAdvice advice = new LogTraceAdvice(trace);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
