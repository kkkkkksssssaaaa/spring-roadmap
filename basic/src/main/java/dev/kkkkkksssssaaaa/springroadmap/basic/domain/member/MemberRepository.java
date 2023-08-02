package dev.kkkkkksssssaaaa.springroadmap.basic.domain.member;

public interface MemberRepository {

    void save(Member member);
    Member findById(Long id);
}
