package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.proxy.v5;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.AppV1Config;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.AppV2Config;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.proxy.v3.LogTraceAdvice;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.logtarce.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import({AppV1Config.class, AppV2Config.class})
// spring boot starter aop 를 패키지 의존성에 등록하게 된다면 자동 프록시 생성기가 빈으로 등록되게 된다.
public class AutoProxyConfig {

    // 자동 프록시 생성기가 모든 Advisor 를 참조하기 때문에, Advisor 만 빈으로 등록해도 된다.
    @Bean
    public Advisor advisor1(LogTrace trace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();

        pointcut.setMappedNames("request*", "order*", "save*");

        LogTraceAdvice advice = new LogTraceAdvice(trace);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
