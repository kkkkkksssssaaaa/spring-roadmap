package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.advisor;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.common.advice.TimeAdvice;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.common.service.ServiceImpl;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.common.service.ServiceInterface;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

class AdvisorTest {

    @Test
    void test1() {
        ServiceInterface target = new ServiceImpl();

        ProxyFactory factory = new ProxyFactory(target);

        // 가장 기본적인 어드바이저
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(
            // 이 옵션을 주면 어느 대상이던간에 어드바이스를 적용한다는 뜻이 된다. == filter: *
            // 참고로 Advice 만 받는 생성자에서도 내부적으로는 Pointcut.TRUE 를 기본적으로 할당해주고 있다.
            Pointcut.TRUE,
            new TimeAdvice()
        );
        factory.addAdvisor(advisor);

        ServiceInterface proxy = (ServiceInterface) factory.getProxy();

        proxy.save();
        proxy.find();
    }
}
