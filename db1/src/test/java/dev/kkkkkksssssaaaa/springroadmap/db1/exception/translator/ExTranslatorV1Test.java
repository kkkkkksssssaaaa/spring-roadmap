package dev.kkkkkksssssaaaa.springroadmap.db1.exception.translator;

import dev.kkkkkksssssaaaa.springroadmap.db1.domain.Member;
import dev.kkkkkksssssaaaa.springroadmap.db1.repository.exception.MyDbException;
import dev.kkkkkksssssaaaa.springroadmap.db1.repository.exception.MyDuplicateKeyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import static dev.kkkkkksssssaaaa.springroadmap.db1.connection.ConnectionConst.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Slf4j
class ExTranslatorV1Test {

    private Repository repository;
    private Service service;

    @Test
    void duplicateKeySave() {
        service.create("myId");
        assertDoesNotThrow(() -> service.create("myId"));
    }

    @BeforeEach
    void init() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        repository = new Repository(dataSource);

        service = new Service(repository);
    }

    @Slf4j
    @RequiredArgsConstructor
    static class Service {

        private final Repository repository;

        public void create(String memberId) {
            try {
                repository.save(new Member(memberId, 0));
                log.info("saveId={}", memberId);
            } catch (MyDuplicateKeyException e) {
                log.info("키 중복, 복구 시도");

                String retryId = generateNewId(memberId);
                log.info("retryId={}", retryId);

                repository.save(new Member(retryId, 0));
            } catch (MyDbException e) {
                log.error("데이터 접근 계층 예외", e);

                throw e;
            }
        }

        private String generateNewId(String memberId) {
            return memberId + new Random().nextInt(10000);
        }
    }

    @RequiredArgsConstructor
    static class Repository {

        private final DataSource dataSource;

        public Member save(Member member) {
            String sql = "insert into member(member_id, money) values(?, ?)";
            Connection conn = null;
            PreparedStatement pstmt = null;

            try {
                conn = dataSource.getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, member.getMemberId());
                pstmt.setInt(2, member.getMoney());

                pstmt.executeUpdate();

                return member;
            } catch (SQLException e) {
                if (e.getErrorCode() == 23505) {
                    throw new MyDuplicateKeyException(e);
                }

                throw new MyDbException(e);
            } finally {
                JdbcUtils.closeStatement(pstmt);
                JdbcUtils.closeConnection(conn);
            }
        }
    }
}
