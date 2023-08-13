package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.proxy.v1;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.proxy.v1.concrete.OrderControllerConcreteProxy;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.proxy.v1.concrete.OrderRepositoryConcreteProxy;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.proxy.v1.concrete.OrderServiceConcreteProxy;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.logtarce.LogTrace;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.app.v2.OrderControllerV2;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.app.v2.OrderRepositoryV2;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.app.v2.OrderServiceV2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class ConcreteProxyConfig {

//    @Bean
    public OrderRepositoryV2 orderRepositoryV2(LogTrace trace) {
        OrderRepositoryV2 target = new OrderRepositoryV2();

        return new OrderRepositoryConcreteProxy(target, trace);
    }

//    @Bean
    public OrderServiceV2 orderServiceV2(LogTrace trace) {
        OrderServiceV2 target = new OrderServiceV2(orderRepositoryV2(trace));

        return new OrderServiceConcreteProxy(target, trace);
    }

//    @Bean
    public OrderControllerV2 orderControllerV2(LogTrace trace) {
        OrderControllerV2 target = new OrderControllerV2(orderServiceV2(trace));

        return new OrderControllerConcreteProxy(target, trace);
    }
}
