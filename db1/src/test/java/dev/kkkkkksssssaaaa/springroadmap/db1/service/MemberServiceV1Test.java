package dev.kkkkkksssssaaaa.springroadmap.db1.service;

import dev.kkkkkksssssaaaa.springroadmap.db1.domain.Member;
import dev.kkkkkksssssaaaa.springroadmap.db1.repository.MemberRepositoryV1;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import java.sql.SQLException;

import static dev.kkkkkksssssaaaa.springroadmap.db1.connection.ConnectionConst.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 기본 동작, 트랜잭션이 없어서 문제가 발생함
 */
class MemberServiceV1Test {

    public static final String MEMBER_A = "memberA";
    public static final String MEMBER_B = "memberB";
    public static final String MEMBER_EX = "ex";

    private MemberRepositoryV1 repository;
    private MemberServiceV1 service;

    @BeforeEach
    void beforeEach() {
        DataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);

        repository = new MemberRepositoryV1(dataSource);
        service = new MemberServiceV1(repository);
    }

    @AfterEach
    void afterEach() throws SQLException {
        repository.delete(MEMBER_A);
        repository.delete(MEMBER_B);
        repository.delete(MEMBER_EX);
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
        service.accountTransfer(a.getMemberId(), b.getMemberId(), 2000);

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

        assertEquals(8000, findA.getMoney());
        assertEquals(10000, findEx.getMoney());
    }
}