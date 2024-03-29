package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.strategy;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.strategy.code.ContextV2;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.strategy.code.Strategy;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.strategy.code.StrategyLogic1;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.strategy.code.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    @Test
    void strategyV1() {
        ContextV2 context = new ContextV2();

        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

    @Test
    void strategyV2() {
        ContextV2 context = new ContextV2();

        context.execute(new Strategy() {

            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        });
        context.execute(new Strategy() {

            @Override
            public void call() {
                log.info("비지니스 로직2 실행");
            }
        });
    }

    @Test
    void strategyV3() {
        ContextV2 context = new ContextV2();

        context.execute(() -> log.info("비지니스 로직1 실행"));
        context.execute(() -> log.info("비지니스 로직2 실행"));
    }
}
