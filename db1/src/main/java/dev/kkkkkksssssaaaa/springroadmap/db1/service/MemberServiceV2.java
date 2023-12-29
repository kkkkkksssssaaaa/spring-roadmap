package dev.kkkkkksssssaaaa.springroadmap.db1.service;

import dev.kkkkkksssssaaaa.springroadmap.db1.domain.Member;
import dev.kkkkkksssssaaaa.springroadmap.db1.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 트랜잭션 - 파라미터 연동, 풀을 고려한 종료
 * */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV2 {

    private final DataSource dataSource;
    private final MemberRepositoryV2 repository;

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        Connection conn = dataSource.getConnection();

        try {
            conn.setAutoCommit(false);

            bizLogic(conn, fromId, toId, money);

            // 로직이 성공 했다면 커밋
            conn.commit();
        } catch (Exception e) {
            // 로직이 실패 했다면 롤백
            conn.rollback();

            throw new IllegalStateException(e);
        } finally {
            release(conn);
        }
    }

    private void bizLogic(
        Connection conn,
        String fromId,
        String toId,
        int money
    ) throws SQLException {
        // 비즈니스 로직 시작
        Member fromMember = repository.findById(conn, fromId);
        Member toMember = repository.findById(conn, toId);

        repository.update(conn, fromId, fromMember.getMoney() - money);

        validation(toMember);

        repository.update(conn, toId, toMember.getMoney() + money);
    }

    private void release(Connection conn) {
        if (null != conn) {
            try {
                // setAutoCommit 을 변경하지 않을 경우, 자동 커밋 모드가 false 인 채로 커넥션 풀에 돌아가게 된다.
                conn.setAutoCommit(true);
                conn.close();
            } catch (Exception e) {
                log.error("error", e);
            }
        }
    }

    private void validation(Member toMember) {
        if (toMember.getMemberId().equals("ex")) {
            throw new IllegalStateException("이체 중 예외 발생");
        }
    }
}
