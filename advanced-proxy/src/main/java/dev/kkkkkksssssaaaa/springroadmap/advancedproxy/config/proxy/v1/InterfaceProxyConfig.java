package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.proxy.v1;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.proxy.v1.abstraction.OrderControllerInterfaceProxy;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.proxy.v1.abstraction.OrderRepositoryInterfaceProxy;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.proxy.v1.abstraction.OrderServiceInterfaceProxy;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.logtarce.LogTrace;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v1.OrderControllerV1;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v1.OrderControllerV1Impl;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v1.OrderRepositoryV1;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v1.OrderRepositoryV1Impl;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v1.OrderServiceV1;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v1.OrderServiceV1Impl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {

    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace trace) {
        OrderControllerV1Impl controllerImpl = new OrderControllerV1Impl(orderServiceV1(trace));

        return new OrderControllerInterfaceProxy(controllerImpl, trace);
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace trace) {
        OrderServiceV1Impl serviceImpl = new OrderServiceV1Impl(orderRepositoryV1(trace));

        return new OrderServiceInterfaceProxy(serviceImpl, trace);
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace trace) {
        OrderRepositoryV1Impl repositoryImpl = new OrderRepositoryV1Impl();

        return new OrderRepositoryInterfaceProxy(repositoryImpl, trace);
    }
}
