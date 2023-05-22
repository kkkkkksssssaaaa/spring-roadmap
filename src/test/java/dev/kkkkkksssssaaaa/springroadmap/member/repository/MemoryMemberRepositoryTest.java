package dev.kkkkkksssssaaaa.springroadmap.member.repository;

import dev.kkkkkksssssaaaa.springroadmap.member.domain.Member;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    private final MemberRepository repository = new MemoryMemberRepository();

    @Test
    void save() {
        Member member = new Member();

        member.setName("string");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        assertEquals(member, result);
    }

    @Test
    void findByName() {
        Member member1 = new Member();

        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();

        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertEquals(member1, result);
    }
}