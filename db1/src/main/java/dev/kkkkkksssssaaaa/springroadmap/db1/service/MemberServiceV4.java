package dev.kkkkkksssssaaaa.springroadmap.db1.service;

import dev.kkkkkksssssaaaa.springroadmap.db1.domain.Member;
import dev.kkkkkksssssaaaa.springroadmap.db1.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * 예외 누수 문제 해결
 * */
@Slf4j
public class MemberServiceV4 {

    private final MemberRepository repository;

    public MemberServiceV4(MemberRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        bizLogic(fromId, toId, money);
    }

    private void bizLogic(
        String fromId,
        String toId,
        int money
    ) {
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
