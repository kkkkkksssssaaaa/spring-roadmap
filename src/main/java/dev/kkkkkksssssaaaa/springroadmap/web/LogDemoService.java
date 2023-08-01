package dev.kkkkkksssssaaaa.springroadmap.web;

import dev.kkkkkksssssaaaa.springroadmap.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final ObjectProvider<MyLogger> logger;

    public void logic(String testId) {
        logger.getObject().log("service id = " + testId);
    }
}
