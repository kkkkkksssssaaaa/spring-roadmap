package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.decorator;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.decorator.code.Component;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.decorator.code.DecoratorPatternClient;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.decorator.code.MessageDecorator;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.decorator.code.RealComponent;
import org.junit.jupiter.api.Test;

public class DecoratorPatternTest {

    @Test
    void noDecorator() {
        Component component = new RealComponent();

        DecoratorPatternClient client = new DecoratorPatternClient(component);

        client.execute();
    }

    @Test
    void decorator1() {
        Component realComponent = new RealComponent();
        Component decorator = new MessageDecorator(realComponent);

        DecoratorPatternClient client = new DecoratorPatternClient(decorator);

        client.execute();
    }
}
