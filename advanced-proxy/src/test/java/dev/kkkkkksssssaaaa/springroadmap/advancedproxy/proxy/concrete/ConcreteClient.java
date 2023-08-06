package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.concrete;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteClient {

    private ConcreteLogic logic;

    public ConcreteClient(ConcreteLogic logic) {
        this.logic = logic;
    }

    public void execute() {
        logic.operation();
    }
}
