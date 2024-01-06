package dev.kkkkkksssssaaaa.springroadmap.db1.service;

import dev.kkkkkksssssaaaa.springroadmap.db1.domain.Member;
import dev.kkkkkksssssaaaa.springroadmap.db1.repository.MemberRepositoryV3;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

import static dev.kkkkkksssssaaaa.springroadmap.db1.connection.ConnectionConst.PASSWORD;
import static dev.kkkkkksssssaaaa.springroadmap.db1.connection.ConnectionConst.URL;
import static dev.kkkkkksssssaaaa.springroadmap.db1.connection.ConnectionConst.USERNAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 트랜잭션 - Datasource, transactionManager 자동 등록
 */
@Slf4j
@SpringBootTest
class MemberServiceV3_4Test {

    public static final String MEMBER_A = "memberA";
    public static final String MEMBER_B = "memberB";
    public static final String MEMBER_EX = "ex";

    @Autowired private MemberRepositoryV3 repository;
    @Autowired private MemberServiceV3_3 service;

    @AfterEach
    void afterEach() throws SQLException {
        repository.delete(MEMBER_A);
        repository.delete(MEMBER_B);
        repository.delete(MEMBER_EX);
    }

    @Test
    void aopCheck() {
        log.info("memberService class={}", service.getClass());
        log.info("memberRepository class={}", repository.getClass());

        assertTrue(AopUtils.isAopProxy(service));
        assertFalse(AopUtils.isAopProxy(repository));
    }

    @Test
    @DisplayName("정상 이체")
    void accountTransfer() throws SQLException {
        // given
        Member a = new Member(MEMBER_A, 10000);
        Member b = new Member(MEMBER_B, 10000);

        repository.save(a);
        repository.save(b);

        // when
        log.info("START TX");
        service.accountTransfer(a.getMemberId(), b.getMemberId(), 2000);
        log.info("END TX");

        // then
        Member findA = repository.findById(a.getMemberId());
        Member findB = repository.findById(b.getMemberId());

        assertEquals(8000, findA.getMoney());
        assertEquals(12000, findB.getMoney());
    }

    @Test
    @DisplayName("예외 발생")
    void accountTransferEx() throws SQLException {
        // given
        Member a = new Member(MEMBER_A, 10000);
        Member ex = new Member(MEMBER_EX, 10000);

        repository.save(a);
        repository.save(ex);

        // when
        assertThrows(
            IllegalStateException.class,
            () -> service.accountTransfer(a.getMemberId(), ex.getMemberId(), 2000)
        );

        // then
        Member findA = repository.findById(a.getMemberId());
        Member findEx = repository.findById(ex.getMemberId());

        assertEquals(10000, findA.getMoney());
        assertEquals(10000, findEx.getMoney());
    }

    @TestConfiguration
    static class TestConfig {

        private final DataSource dataSource;

        public TestConfig(DataSource dataSource) {
            this.dataSource = dataSource;
        }

        @Bean
        MemberRepositoryV3 repository() {
            return new MemberRepositoryV3(dataSource);
        }

        @Bean
        MemberServiceV3_3 service() {
            return new MemberServiceV3_3(repository());
        }
    }
}