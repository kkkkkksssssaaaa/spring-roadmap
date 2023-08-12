package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.proxyfactory;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.common.advice.TimeAdvice;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.common.service.ConcreteService;
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

    @Test
    void 구체_클래스만_있다면_CGLib_사용() {
        ConcreteService service = new ConcreteService();

        ProxyFactory proxyFactory = new ProxyFactory(service);
        proxyFactory.addAdvice(new TimeAdvice());

        ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();

        log.info("targetClass={}", service.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.call();

        assertTrue(AopUtils.isAopProxy(proxy));
        assertFalse(AopUtils.isJdkDynamicProxy(proxy));
        assertTrue(AopUtils.isCglibProxy(proxy));
    }

    @Test
    void proxyTargetClass옵션을_사용하면_인터페이스가_있어도_CGLib를_사용하고_클래스_기반_프록시를_사용() {
        ServiceInterface service = new ServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory(service);
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.addAdvice(new TimeAdvice());

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        log.info("targetClass={}", service.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.save();

        assertTrue(AopUtils.isAopProxy(proxy));
        assertFalse(AopUtils.isJdkDynamicProxy(proxy));
        assertTrue(AopUtils.isCglibProxy(proxy));
    }
}
