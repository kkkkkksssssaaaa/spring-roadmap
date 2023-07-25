package dev.kkkkkksssssaaaa.springroadmap.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class SingletonTest {

    @Test
    void 싱글톤_패턴을_적용한_객체_사용() {
        SingletonService service1 = SingletonService.getInstance();
        SingletonService service2 = SingletonService.getInstance();

        assertEquals(service1, service2);
        assertSame(service1, service2);
    }
}
