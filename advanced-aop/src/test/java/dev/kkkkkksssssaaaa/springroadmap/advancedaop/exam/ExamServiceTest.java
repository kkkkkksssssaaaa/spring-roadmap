package dev.kkkkkksssssaaaa.springroadmap.advancedaop.exam;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ExamServiceTest {

    @Autowired private ExamService service;

    @Test
    void test() {
        for (int i = 0; i < 5; i++) {
            log.info("client request i={}", i);
            service.request("data" + i);
        }
    }
}