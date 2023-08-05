package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v1.OrderControllerV1;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v1.OrderControllerV1Impl;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v1.OrderRepositoryV1;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v1.OrderRepositoryV1Impl;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v1.OrderServiceV1;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v1.OrderServiceV1Impl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppV1Config {

    @Bean
    public OrderControllerV1 controllerV1() {
        return new OrderControllerV1Impl(orderServiceV1());
    }

    @Bean
    public OrderServiceV1 orderServiceV1() {
        return new OrderServiceV1Impl(orderRepositoryV1());
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1() {
        return new OrderRepositoryV1Impl();
    }
}
