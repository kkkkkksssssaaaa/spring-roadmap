package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.concrete;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator extends ConcreteLogic {

    private ConcreteLogic target;

    public TimeDecorator(ConcreteLogic target) {
        this.target = target;
    }

    @Override
    public String operation() {
        log.info("TimeDecorator 실행");

        long startTime = System.currentTimeMillis();

        String result = this.target.operation();

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;

        log.info("TimeDecorator 종료 resultTime={}ms", resultTime);

        return result;
    }
}
