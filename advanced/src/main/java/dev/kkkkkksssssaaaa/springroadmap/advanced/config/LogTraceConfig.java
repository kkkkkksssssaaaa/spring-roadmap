package dev.kkkkkksssssaaaa.springroadmap.advanced.config;

import dev.kkkkkksssssaaaa.springroadmap.advanced.trace.logtarce.LogTrace;
import dev.kkkkkksssssaaaa.springroadmap.advanced.trace.logtarce.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }
}
