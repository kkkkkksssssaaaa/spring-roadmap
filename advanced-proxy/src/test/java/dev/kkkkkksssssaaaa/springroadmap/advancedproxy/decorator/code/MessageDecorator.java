package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component {

    private final Component target;

    public MessageDecorator(Component target) {
        this.target = target;
    }

    @Override
    public String operation() {
        log.info("MessageDecorator 실행");

        String operator = this.target.operation();
        String decoratorResult = "***** " + operator + " *****";

        log.info("MessageDecorator 적용 전={}, 적용 후={}", operator, decoratorResult);

        return decoratorResult;
    }
}
