package dev.kkkkkksssssaaaa.springroadmap.db1.service;

import dev.kkkkkksssssaaaa.springroadmap.db1.domain.Member;
import dev.kkkkkksssssaaaa.springroadmap.db1.repository.MemberRepositoryV3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.SQLException;

/**
 * 트랜잭션 - 트랜잭션 매니저
 * */
@Slf4j
public class MemberServiceV3_2 {

    private final TransactionTemplate txTemplate;
    private final MemberRepositoryV3 repository;

    public MemberServiceV3_2(PlatformTransactionManager txManager, MemberRepositoryV3 repository) {
        this.txTemplate = new TransactionTemplate(txManager);
        this.repository = repository;
    }

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        txTemplate.executeWithoutResult(status -> {
            try {
                bizLogic(fromId, toId, money);
            } catch(SQLException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    private void bizLogic(
        String fromId,
        String toId,
        int money
    ) throws SQLException {
        // 비즈니스 로직 시작
        Member fromMember = repository.findById(fromId);
        Member toMember = repository.findById(toId);

        repository.update(fromId, fromMember.getMoney() - money);

        validation(toMember);

        repository.update(toId, toMember.getMoney() + money);
    }

    private void validation(Member toMember) {
        if (toMember.getMemberId().equals("ex")) {
            throw new IllegalStateException("이체 중 예외 발생");
        }
    }
}
