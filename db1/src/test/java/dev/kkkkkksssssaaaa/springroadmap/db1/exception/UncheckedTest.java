package dev.kkkkkksssssaaaa.springroadmap.db1.exception;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
class UncheckedTest {

    @Test
    void uncheckedCatch() {
        Service service = new Service();
        assertDoesNotThrow(service::callCatch);
    }

    @Test
    void uncheckedThrows() {
        Service service = new Service();
        assertThrows(MyUncheckedException.class, service::callThrows);
    }

    static class MyUncheckedException extends RuntimeException {

        public MyUncheckedException(String message) {
            super(message);
        }
    }

    static class Service {

        private Repository repository = new Repository();

        public void callCatch() {
            try {
                repository.call();
            } catch (MyUncheckedException e) {
                log.error("예외 처리, message={}", e.getMessage(), e);
            }
        }

        public void callThrows() throws MyUncheckedException {
            repository.call();
        }
    }

    static class Repository {

        public void call() {
            throw new MyUncheckedException("ex");
        }
    }
}
