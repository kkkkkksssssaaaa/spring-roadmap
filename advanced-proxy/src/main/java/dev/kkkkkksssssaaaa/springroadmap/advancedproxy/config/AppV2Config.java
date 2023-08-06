package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v2.OrderControllerV2;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v2.OrderRepositoryV2;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v2.OrderServiceV2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppV2Config {

    @Bean
    public OrderControllerV2 controllerV2() {
        return new OrderControllerV2(orderServiceV2());
    }

    @Bean
    public OrderServiceV2 orderServiceV2() {
        return new OrderServiceV2(orderRepositoryV2());
    }

    @Bean
    public OrderRepositoryV2 orderRepositoryV2() {
        return new OrderRepositoryV2();
    }
}
