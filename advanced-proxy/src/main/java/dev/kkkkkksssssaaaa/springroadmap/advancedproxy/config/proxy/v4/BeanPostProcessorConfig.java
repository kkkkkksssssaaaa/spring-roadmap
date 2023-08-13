package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.proxy.v4;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.AppV1Config;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.AppV2Config;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.proxy.v3.LogTraceAdvice;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.proxy.v4.postprocessor.PackageLogTraceProxyPostProcessor;
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
// ComponentScan 을 수행하지 않는 설정을 수동 등록
@Import({AppV1Config.class, AppV2Config.class})
public class BeanPostProcessorConfig {

    @Bean
    public PackageLogTraceProxyPostProcessor logTraceProxyPostProcessor(LogTrace trace) {
        return new PackageLogTraceProxyPostProcessor(
            "dev.kkkkkksssssaaaa.springroadmap.advancedproxy.app",
            getAdvisor(trace)
        );
    }

    private Advisor getAdvisor(LogTrace trace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();

        pointcut.setMappedNames("request*", "order*", "save*");

        LogTraceAdvice advice = new LogTraceAdvice(trace);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
