package dev.kkkkkksssssaaaa.springroadmap.web;

import dev.kkkkkksssssaaaa.springroadmap.common.MyLogger;
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
