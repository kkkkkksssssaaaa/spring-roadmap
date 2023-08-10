package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.proxyfactory;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.common.advice.TimeAdvice;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.common.service.ServiceImpl;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class ProxyFactoryTest {

    @Test
    void 인터페이스가_있다면_JDK_DynamicProxy_사용() {
        ServiceInterface service = new ServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory(service);
        proxyFactory.addAdvice(new TimeAdvice());

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        log.info("targetClass={}", service.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.save();

        assertTrue(AopUtils.isAopProxy(proxy));
        assertTrue(AopUtils.isJdkDynamicProxy(proxy));
        assertFalse(AopUtils.isCglibProxy(proxy));
    }
}
