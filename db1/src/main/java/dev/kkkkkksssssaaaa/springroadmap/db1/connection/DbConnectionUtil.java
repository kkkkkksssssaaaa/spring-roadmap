package dev.kkkkkksssssaaaa.springroadmap.db1.connection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class DbConnectionUtil {

    public static Connection getConnection() {
        try {
            Connection connection =
                DriverManager.getConnection(
                    ConnectionConst.URL,
                    ConnectionConst.USERNAME,
                    ConnectionConst.PASSWORD
                );

            log.info("get connection={} class={}", connection, connection.getClass());

            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }
}
