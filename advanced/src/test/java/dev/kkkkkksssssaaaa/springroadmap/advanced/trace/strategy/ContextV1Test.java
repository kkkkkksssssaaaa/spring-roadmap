package dev.kkkkkksssssaaaa.springroadmap.advanced.trace.strategy;

import dev.kkkkkksssssaaaa.springroadmap.advanced.trace.strategy.code.ContextV1;
import dev.kkkkkksssssaaaa.springroadmap.advanced.trace.strategy.code.Strategy;
import dev.kkkkkksssssaaaa.springroadmap.advanced.trace.strategy.code.StrategyLogic1;
import dev.kkkkkksssssaaaa.springroadmap.advanced.trace.strategy.code.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class ContextV1Test {

    @Test
    void strategyV0() {
        logic1();
        logic2();
    }

    @Test
    void strategyV1() {
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(strategyLogic1);

        contextV1.execute();

        StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        ContextV1 contextV2 = new ContextV1(strategyLogic2);

        contextV2.execute();
    }

    @Test
    void strategyV2() {
        Strategy strategyLogic1 = new Strategy() {

            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        };

        ContextV1 contextV1 = new ContextV1(strategyLogic1);

        contextV1.execute();

        Strategy strategyLogic2 = new Strategy() {

            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        };

        ContextV1 contextV2 = new ContextV1(strategyLogic2);

        contextV2.execute();
    }

    @Test
    void strategyV3() {
        ContextV1 contextV1 = new ContextV1(new Strategy() {

            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        });

        contextV1.execute();

        ContextV1 contextV2 = new ContextV1(new Strategy() {

            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        });

        contextV2.execute();
    }

    @Test
    void strategyV4() {
        ContextV1 contextV1 = new ContextV1(() -> log.info("비지니스 로직1 실행"));

        contextV1.execute();

        ContextV1 contextV2 = new ContextV1(() -> log.info("비지니스 로직1 실행"));

        contextV2.execute();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();

        // start business logic
        log.info("비지니스 로직1 실행");

        long endTime = System.currentTimeMillis();

        long result = endTime - startTime;

        log.info("resultTime={}", result);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();

        // start business logic
        log.info("비지니스 로직2 실행");

        long endTime = System.currentTimeMillis();

        long result = endTime - startTime;

        log.info("resultTime={}", result);
    }
}
