package dev.kkkkkksssssaaaa.springroadmap.db1.service;

import dev.kkkkkksssssaaaa.springroadmap.db1.domain.Member;
import dev.kkkkkksssssaaaa.springroadmap.db1.repository.MemberRepository;
import dev.kkkkkksssssaaaa.springroadmap.db1.repository.MemberRepositoryV5;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 트랜잭션 - Datasource, transactionManager 자동 등록
 */
@Slf4j
@SpringBootTest
class MemberServiceV4Test {

    public static final String MEMBER_A = "memberA";
    public static final String MEMBER_B = "memberB";
    public static final String MEMBER_EX = "ex";

    @Autowired private MemberRepository repository;
    @Autowired private MemberServiceV4 service;

    @AfterEach
    void afterEach() {
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
    void accountTransfer() {
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
    void accountTransferEx() {
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
        MemberRepository repository() {
            return new MemberRepositoryV5(dataSource);
        }

        @Bean
        MemberServiceV4 service() {
            return new MemberServiceV4(repository());
        }
    }
}