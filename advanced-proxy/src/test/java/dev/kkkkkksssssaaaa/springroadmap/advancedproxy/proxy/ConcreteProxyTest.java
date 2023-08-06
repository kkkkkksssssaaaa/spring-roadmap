package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.concrete.ConcreteClient;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.concrete.ConcreteLogic;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.concrete.TimeDecorator;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {

    @Test
    void noProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient concreteClient = new ConcreteClient(concreteLogic);

        concreteClient.execute();
    }

    @Test
    void addProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        TimeDecorator timeDecorator = new TimeDecorator(concreteLogic);

        ConcreteClient concreteClient = new ConcreteClient(timeDecorator);

        concreteClient.execute();
    }
}
