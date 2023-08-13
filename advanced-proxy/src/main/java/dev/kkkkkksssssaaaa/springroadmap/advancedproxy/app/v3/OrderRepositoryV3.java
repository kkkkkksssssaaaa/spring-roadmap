package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.app.v3;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.common.ThreadSleep;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV3 {

    public void save(String itemId) {
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생!");
        }

        ThreadSleep.toMillis(1000);
    }
}
