package dev.kkkkkksssssaaaa.springroadmap.db1.repository;

import dev.kkkkkksssssaaaa.springroadmap.db1.domain.Member;

public interface MemberRepository {

    Member save(Member member);
    Member findById(String memberId);
    void update(String memberId, int money);
    void delete(String memberId);
}
