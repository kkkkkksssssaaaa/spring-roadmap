package dev.kkkkkksssssaaaa.springroadmap.example.scope;

import jakarta.inject.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingletonWithPrototypeBeanTest {

    @Test
    void findPrototype() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find bean1");
        PrototypeBean bean1 = context.getBean(PrototypeBean.class);
        bean1.add();

        assertEquals(1, bean1.get());

        System.out.println("find bean2");
        PrototypeBean bean2 = context.getBean(PrototypeBean.class);
        bean2.add();

        assertEquals(1, bean2.get());
    }

    @Test
    void singletonUsePrototypeBean() {
        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(
                ClientBean.class,
                PrototypeBean.class
            );

        System.out.println("find bean1");
        ClientBean bean1 = context.getBean(ClientBean.class);

        System.out.println("find bean2");
        ClientBean bean2 = context.getBean(ClientBean.class);

        assertEquals(1, bean1.add());
        assertEquals(1, bean2.add());
    }

    @Scope("singleton")
    static class ClientBean {

        @Autowired
        private Provider<PrototypeBean> objectProvider;

        public int add() {
            PrototypeBean prototypeBean = objectProvider.get();
            prototypeBean.add();
            return prototypeBean.get();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {

        private int count = 0;

        public void add() {
            this.count++;
        }

        public int get() {
            return this.count;
        }
    }
}
