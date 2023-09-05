package dev.kkkkkksssssaaaa.springroadmap.advancedaop.internal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV3 {

    private final InternalService internal;

    @Autowired
    public CallServiceV3(InternalService internal) {
        this.internal = internal;
    }

    public void external() {
        log.info("call external");
        internal.internal();
    }
}
