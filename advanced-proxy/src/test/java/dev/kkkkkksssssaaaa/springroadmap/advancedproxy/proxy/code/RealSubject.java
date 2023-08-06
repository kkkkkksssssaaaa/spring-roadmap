package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.code;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.common.ThreadSleep;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealSubject implements Subject {

    @Override
    public String operation() {
        log.info("실제 객체 호출");

        ThreadSleep.toMillis(1000);

        return "data";
    }
}
