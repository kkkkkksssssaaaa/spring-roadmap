package dev.kkkkkksssssaaaa.springroadmap.advancedaop;

import dev.kkkkkksssssaaaa.springroadmap.advancedaop.order.OrderRepository;
import dev.kkkkkksssssaaaa.springroadmap.advancedaop.order.OrderService;
import dev.kkkkkksssssaaaa.springroadmap.advancedaop.order.aop.AspectV6Advice;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@Import({
    AspectV6Advice.class
})
@SpringBootTest
public class AopTest {

    @Autowired
    OrderService service;

    @Autowired
    OrderRepository repository;

    @Test
    void aopInfo() {
        log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(service));
        log.info("isAopProxy, orderRepository={}", AopUtils.isAopProxy(repository));
    }

    @Test
    void success() {
        service.orderItem("itemA");
    }

    @Test
    void exception() {
        assertThrows(IllegalStateException.class, () -> service.orderItem("ex"));
    }
}
