package dev.kkkkkksssssaaaa.springroadmap.db1.exception;

import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CheckedAppTest {

    @Test
    void checked() {
        Controller controller = new Controller();

        assertThrows(Exception.class, controller::request);
    }

    static class Controller {

        Service service = new Service();

        public void request() throws SQLException, ConnectException {
            service.logic();
        }
    }

    static class Service {

        Repository repository = new Repository();
        NetworkClient client = new NetworkClient();

        public void logic() throws ConnectException, SQLException {
            repository.call();
            client.call();
        }
    }

    static class NetworkClient {

        public void call() throws ConnectException {
            throw new ConnectException("연결 실패");
        }
    }

    static class Repository {

        public void call() throws SQLException {
            throw new SQLException("ex");
        }
    }
}
