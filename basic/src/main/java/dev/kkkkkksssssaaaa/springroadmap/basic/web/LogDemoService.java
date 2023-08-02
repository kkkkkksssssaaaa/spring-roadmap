package dev.kkkkkksssssaaaa.springroadmap.basic.web;

import dev.kkkkkksssssaaaa.springroadmap.basic.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final MyLogger logger;

    public void logic(String testId) {
        logger.log("service id = " + testId);
    }
}
