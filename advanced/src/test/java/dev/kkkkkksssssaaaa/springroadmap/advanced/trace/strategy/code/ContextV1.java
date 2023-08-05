package dev.kkkkkksssssaaaa.springroadmap.advanced.trace.strategy.code;

import lombok.extern.slf4j.Slf4j;

/**
 * 필드에 전력을 보관하는 방식
 * */
@Slf4j
public class ContextV1 {

    private Strategy strategy;

    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {
        long startTime = System.currentTimeMillis();

        // start business logic
        strategy.call();

        long endTime = System.currentTimeMillis();

        long result = endTime - startTime;

        log.info("resultTime={}", result);
    }
}
