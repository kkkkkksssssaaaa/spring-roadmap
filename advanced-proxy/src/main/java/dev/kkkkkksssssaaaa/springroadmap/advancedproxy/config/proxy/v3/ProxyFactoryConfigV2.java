package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.proxy.v3;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.logtarce.LogTrace;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v2.OrderControllerV2;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v2.OrderRepositoryV2;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v2.OrderServiceV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProxyFactoryConfigV2 {

    @Bean
    public OrderControllerV2 orderControllerV2(LogTrace trace) {
        OrderControllerV2 target = new OrderControllerV2(orderServiceV2(trace));

        ProxyFactory factory = new ProxyFactory(target);
        factory.addAdvisor(getAdvisor(trace));

        OrderControllerV2 proxy = (OrderControllerV2) factory.getProxy();

        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), trace.getClass());

        return proxy;
    }

    @Bean
    public OrderServiceV2 orderServiceV2(LogTrace trace) {
        OrderServiceV2 target = new OrderServiceV2(orderRepositoryV2(trace));

        ProxyFactory factory = new ProxyFactory(target);
        factory.addAdvisor(getAdvisor(trace));

        OrderServiceV2 proxy = (OrderServiceV2) factory.getProxy();

        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), trace.getClass());

        return proxy;
    }

    @Bean
    public OrderRepositoryV2 orderRepositoryV2(LogTrace trace) {
        OrderRepositoryV2 target = new OrderRepositoryV2();

        ProxyFactory factory = new ProxyFactory(target);
        factory.addAdvisor(getAdvisor(trace));

        OrderRepositoryV2 proxy = (OrderRepositoryV2) factory.getProxy();

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
