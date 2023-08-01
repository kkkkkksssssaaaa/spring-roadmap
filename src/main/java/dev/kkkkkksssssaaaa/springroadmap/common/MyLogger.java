package dev.kkkkkksssssaaaa.springroadmap.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestUrl;

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public void log(String message) {
        System.out.printf("[%s].[%s] %s\n", uuid, requestUrl, message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.printf("[%s] request scope bean create: " + this, uuid);
    }

    @PreDestroy
    public void close() {
        System.out.printf("[%s] request scope bean close: " + this, uuid);
    }
}
