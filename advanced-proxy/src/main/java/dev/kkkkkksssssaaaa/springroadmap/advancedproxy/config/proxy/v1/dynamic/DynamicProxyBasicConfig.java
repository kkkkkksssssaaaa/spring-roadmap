package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.proxy.v1.dynamic;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.proxy.v1.dynamic.handler.LogTraceBasicHandler;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.logtarce.LogTrace;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v1.OrderControllerV1;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v1.OrderControllerV1Impl;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v1.OrderRepositoryV1;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v1.OrderRepositoryV1Impl;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v1.OrderServiceV1;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v1.OrderServiceV1Impl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyBasicConfig {

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace trace) {
        OrderRepositoryV1 target = new OrderRepositoryV1Impl();

        return (OrderRepositoryV1) Proxy.newProxyInstance(
            OrderRepositoryV1.class.getClassLoader(),
            new Class[]{OrderRepositoryV1.class},
            new LogTraceBasicHandler(target, trace)
        );
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace trace) {
        OrderServiceV1 target = new OrderServiceV1Impl(orderRepositoryV1(trace));

        return (OrderServiceV1) Proxy.newProxyInstance(
            OrderServiceV1.class.getClassLoader(),
            new Class[]{OrderServiceV1.class},
            new LogTraceBasicHandler(target, trace)
        );
    }

    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace trace) {
        OrderControllerV1 target = new OrderControllerV1Impl(orderServiceV1(trace));

        return (OrderControllerV1) Proxy.newProxyInstance(
            OrderControllerV1.class.getClassLoader(),
            new Class[]{OrderControllerV1.class},
            new LogTraceBasicHandler(target, trace)
        );
    }
}
