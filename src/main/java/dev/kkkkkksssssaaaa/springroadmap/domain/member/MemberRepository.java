package dev.kkkkkksssssaaaa.springroadmap.domain.member;

public interface MemberRepository {

    void save(Member member);
    Member findById(Long id);
}
