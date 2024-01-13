package dev.kkkkkksssssaaaa.springroadmap.db1.exception.translator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static dev.kkkkkksssssaaaa.springroadmap.db1.connection.ConnectionConst.PASSWORD;
import static dev.kkkkkksssssaaaa.springroadmap.db1.connection.ConnectionConst.URL;
import static dev.kkkkkksssssaaaa.springroadmap.db1.connection.ConnectionConst.USERNAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class SpringExceptionTranslatorTest {

    private DataSource dataSource;

    @BeforeEach
    void init() {
        dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
    }

    @Test
    void sqlExceptionErrorCode() {
        String sql = "select bad grammer";

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            int errorCode = e.getErrorCode();
            log.info("errorCode={}", errorCode);
            log.info("error", e);

            assertEquals(42122, e.getErrorCode());
        }
    }

    @Test
    void exceptionTranslator() {
        String sql = "select bad grammer";

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            assertEquals(42122, e.getErrorCode());

            SQLErrorCodeSQLExceptionTranslator translator =
                new SQLErrorCodeSQLExceptionTranslator(dataSource);

            DataAccessException exception = translator.translate("select", sql, e);

            log.info("result exception", exception);

            assertEquals(exception.getClass(), BadSqlGrammarException.class);
        }
    }
}
