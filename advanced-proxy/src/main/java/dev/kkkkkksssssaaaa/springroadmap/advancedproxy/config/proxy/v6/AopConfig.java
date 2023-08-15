package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.proxy.v6;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.AppV1Config;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.AppV2Config;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.logtarce.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({AppV1Config.class, AppV2Config.class})
@Configuration
public class AopConfig {

    @Bean
    public LogTraceAspect logTraceAspect(LogTrace trace) {
        return new LogTraceAspect(trace);
    }
}
