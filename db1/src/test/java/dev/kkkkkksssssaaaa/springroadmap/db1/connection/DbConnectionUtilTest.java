package dev.kkkkkksssssaaaa.springroadmap.db1.connection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class DbConnectionUtilTest {

    @Test
    void connection() {
        Connection connection = DbConnectionUtil.getConnection();

        assertNotNull(connection);
    }
}