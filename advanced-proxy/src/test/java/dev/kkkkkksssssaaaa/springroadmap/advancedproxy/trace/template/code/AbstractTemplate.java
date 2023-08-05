package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {

    public final void execute() {
        long startTime = System.currentTimeMillis();

        // start business logic
        call();

        long endTime = System.currentTimeMillis();

        long result = endTime - startTime;

        log.info("resultTime={}", result);
    }

    protected abstract void call();
}
