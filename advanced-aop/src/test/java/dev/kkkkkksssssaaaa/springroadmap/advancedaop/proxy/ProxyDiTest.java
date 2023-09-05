package dev.kkkkkksssssaaaa.springroadmap.advancedaop.proxy;

import dev.kkkkkksssssaaaa.springroadmap.advancedaop.member.MemberService;
import dev.kkkkkksssssaaaa.springroadmap.advancedaop.member.MemberServiceImpl;
import dev.kkkkkksssssaaaa.springroadmap.advancedaop.proxy.code.ProxyDiAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"})
@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"})
@Import(ProxyDiAspect.class)
public class ProxyDiTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberServiceImpl memberServiceImpl;

    @Test
    void go() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberServiceImpl class={}", memberServiceImpl.getClass());

        memberServiceImpl.hello("hello");
    }
}
