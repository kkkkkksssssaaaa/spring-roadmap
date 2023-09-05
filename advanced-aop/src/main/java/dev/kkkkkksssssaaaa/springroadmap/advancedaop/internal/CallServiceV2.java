package dev.kkkkkksssssaaaa.springroadmap.advancedaop.internal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV2 {

    private final ObjectProvider<CallServiceV2> context;

    public CallServiceV2(ObjectProvider<CallServiceV2> bean) {
        this.context = bean;
    }

    public void external() {
        log.info("call external");
        context.getObject().internal();
    }

    public void internal() {
        log.info("call internal");
    }
}
