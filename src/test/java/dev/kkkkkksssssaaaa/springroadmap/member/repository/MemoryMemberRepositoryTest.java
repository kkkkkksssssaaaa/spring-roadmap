package dev.kkkkkksssssaaaa.springroadmap.member.repository;

import dev.kkkkkksssssaaaa.springroadmap.member.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    private final MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    void afterEach() {
        repository.clearStore();
    }

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

    @Test
    void findAll() {
        Member member1 = new Member();

        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();

        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertEquals(2, result.size());
    }
}