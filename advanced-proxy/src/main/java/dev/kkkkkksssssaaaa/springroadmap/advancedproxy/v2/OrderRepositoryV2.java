package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v2;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.common.ThreadSleep;

public class OrderRepositoryV2 {

    public void save(String itemId) {
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생!");
        }

        ThreadSleep.toMillis(1000);
    }
}
