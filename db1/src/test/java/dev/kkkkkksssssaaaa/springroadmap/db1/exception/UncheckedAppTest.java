package dev.kkkkkksssssaaaa.springroadmap.db1.exception;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UncheckedAppTest {

    @Test
    void checked() {
        Controller controller = new Controller();

        assertThrows(Exception.class, controller::request);
        assertThrows(RuntimeSqlException.class, controller::request);
    }

    static class Controller {

        Service service = new Service();

        public void request() {
            service.logic();
        }
    }

    static class Service {

        Repository repository = new Repository();
        NetworkClient client = new NetworkClient();

        public void logic() {
            repository.call();
            client.call();
        }
    }

    static class NetworkClient {

        public void call() throws RuntimeConnectionException {
            throw new RuntimeConnectionException("연결 실패");
        }
    }

    static class Repository {

        public void call() throws RuntimeSqlException {
            try {
                runSql();
            } catch (SQLException e) {
                throw new RuntimeSqlException(e);
            }
        }

        private void runSql() throws SQLException {
            throw new SQLException("ex");
        }
    }

    static class RuntimeConnectionException extends RuntimeException {

        public RuntimeConnectionException(String message) {
            super(message);
        }
    }

    static class RuntimeSqlException extends RuntimeException {

        public RuntimeSqlException(Throwable e) {
            super(e);
        }
    }
}
