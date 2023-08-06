package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.decorator;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.decorator.code.Component;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.decorator.code.DecoratorPatternClient;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.decorator.code.MessageDecorator;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.decorator.code.RealComponent;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.decorator.code.TimeDecorator;
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

    @Test
    void decorator2() {
        Component realComponent = new RealComponent();
        Component messageDecorator = new MessageDecorator(realComponent);
        Component timeDecorator = new TimeDecorator(messageDecorator);

        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);

        client.execute();
    }
}
