package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.strategy.code;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class TemplateCallbackTest {

    @Test
    void callbackV1() {
        TimeLogTemplate template = new TimeLogTemplate();

        template.execute(() -> log.info("비지니스 로직1 실행"));
        template.execute(() -> log.info("비지니스 로직2 실행"));
    }
}
