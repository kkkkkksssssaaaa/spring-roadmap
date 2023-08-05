package dev.kkkkkksssssaaaa.springroadmap.advanced.trace.strategy.code;

import lombok.extern.slf4j.Slf4j;

/**
 * 전략을 필드로 가지는 대신, 메서드 파라미터로 전달 받는 방식
 * */
@Slf4j
public class ContextV2 {

    public void execute(Strategy strategy) {
        long startTime = System.currentTimeMillis();

        // start business logic
        strategy.call();

        long endTime = System.currentTimeMillis();

        long result = endTime - startTime;

        log.info("resultTime={}", result);
    }
}
