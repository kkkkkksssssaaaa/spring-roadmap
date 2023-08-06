package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.common;

public class ThreadSleep {

    public static void toMillis(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
