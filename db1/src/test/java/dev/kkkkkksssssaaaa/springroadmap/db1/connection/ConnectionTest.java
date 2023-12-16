package dev.kkkkkksssssaaaa.springroadmap.db1.connection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static dev.kkkkkksssssaaaa.springroadmap.db1.connection.ConnectionConst.PASSWORD;
import static dev.kkkkkksssssaaaa.springroadmap.db1.connection.ConnectionConst.URL;
import static dev.kkkkkksssssaaaa.springroadmap.db1.connection.ConnectionConst.USERNAME;

@Slf4j
public class ConnectionTest {

    @Test
    void driverManager() throws SQLException {
        Connection conn1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection conn2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        log.info("connection={}, class={}", conn1, conn1.getClass());
        log.info("connection={}, class={}", conn2, conn2.getClass());
    }
}
