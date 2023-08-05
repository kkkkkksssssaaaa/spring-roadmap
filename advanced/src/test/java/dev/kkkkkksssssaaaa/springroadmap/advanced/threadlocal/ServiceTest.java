package dev.kkkkkksssssaaaa.springroadmap.advanced.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ServiceTest {

    private FieldService fieldService = new FieldService();
    private ThreadLocalService threadLocalService = new ThreadLocalService();

    @Test
    void field() {
        log.info("main start");

        Thread threadA = new Thread(() -> fieldService.logic("userA"));
        threadA.setName("thread-A");

        Thread threadB = new Thread(() -> fieldService.logic("userB"));
        threadB.setName("thread-B");

        threadA.start();
        sleep(100); // 동시성 문제 발생 하지 않음

        threadB.start();
        sleep(3000); // 메인 스레드 종료 대기

        log.info("main exit");
    }

    @Test
    void threadLocal() {
        log.info("main start");

        Thread threadA = new Thread(() -> threadLocalService.logic("userA"));
        threadA.setName("thread-A");

        Thread threadB = new Thread(() -> threadLocalService.logic("userB"));
        threadB.setName("thread-B");

        threadA.start();
        sleep(100); // 동시성 문제 발생 하지 않음

        threadB.start();
        sleep(3000); // 메인 스레드 종료 대기

        log.info("main exit");
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
