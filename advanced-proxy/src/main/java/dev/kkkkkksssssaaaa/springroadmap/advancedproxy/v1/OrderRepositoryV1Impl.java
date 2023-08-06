package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v1;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.common.ThreadSleep;

public class OrderRepositoryV1Impl implements OrderRepositoryV1 {

    @Override
    public void save(String itemId) {
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생!");
        }

        ThreadSleep.toMillis(1000);
    }
}
