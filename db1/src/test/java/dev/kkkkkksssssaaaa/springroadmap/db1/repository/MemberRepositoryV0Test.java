package dev.kkkkkksssssaaaa.springroadmap.db1.repository;

import dev.kkkkkksssssaaaa.springroadmap.db1.domain.Member;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        Member member = new Member("memberV0", 10000);
        repository.save(member);
    }
}