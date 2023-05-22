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
}