package dev.kkkkkksssssaaaa.springroadmap.db1.exception;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
class CheckedTest {

    @Test
    void checkedCatch() {
        Service service = new Service();
        assertDoesNotThrow(service::callCatch);
    }

    @Test
    void checkedThrows() {
        Service service = new Service();
        assertThrows(MyCheckedException.class, service::callThrows);
    }

    // Exception 을 상속 받으면 CheckedException 이 된다.
    static class MyCheckedException extends Exception {

        public MyCheckedException(String message) {
            super(message);
        }
    }

    /**
     * CheckedException 은
     * 예외를 잡아서 처리 하거나, 던지거나 둘 중 하나를 필수로 선택 해야 한다
     * */
    static class Service {

        private Repository repository = new Repository();

        // 예외를 잡아서 처리 하는 코드
        public void callCatch() {
            try {
                repository.call();
            } catch (Exception e) {
                log.error("예외 처리, message={}", e.getMessage(), e);
            }
        }

        public void callThrows() throws MyCheckedException {
            repository.call();
        }
    }

    static class Repository {

        public void call() throws MyCheckedException {
            throw new MyCheckedException("ex");
        }
    }
}
