package dev.kkkkkksssssaaaa.springroadmap.advancedaop.proxy;

import dev.kkkkkksssssaaaa.springroadmap.advancedaop.member.MemberService;
import dev.kkkkkksssssaaaa.springroadmap.advancedaop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class ProxyCastingTest {

    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();

        ProxyFactory factory = new ProxyFactory(target);
        factory.setProxyTargetClass(false);

        assertDoesNotThrow(() -> (MemberService) factory.getProxy());
        assertThrows(ClassCastException.class, () -> {
            MemberServiceImpl proxy = (MemberServiceImpl) factory.getProxy();
        });
    }

    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();

        ProxyFactory factory = new ProxyFactory(target);
        factory.setProxyTargetClass(true);

        assertDoesNotThrow(() -> (MemberService) factory.getProxy());
        assertDoesNotThrow(() -> (MemberServiceImpl) factory.getProxy());
    }
}
