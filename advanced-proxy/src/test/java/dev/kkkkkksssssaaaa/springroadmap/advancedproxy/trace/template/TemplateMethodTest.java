package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.template;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.template.code.AbstractTemplate;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.template.code.SubClassLogic1;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class TemplateMethodTest {

    @Test
    void templateMethodV0() {
        logic1();
        logic2();
    }

    @Test
    void templateMethodV1() {
        AbstractTemplate template1 = new SubClassLogic1();

        template1.execute();

        AbstractTemplate template2 = new SubClassLogic2();

        template2.execute();
    }

    @Test
    void templateMethodV2() {
        AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비지니스 로직1 실행");
            }
        };

        template1.execute();

        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비지니스 로직1 실행");
            }
        };

        template2.execute();
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
