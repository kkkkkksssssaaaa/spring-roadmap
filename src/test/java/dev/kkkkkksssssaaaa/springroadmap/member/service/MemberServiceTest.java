package dev.kkkkkksssssaaaa.springroadmap.member.service;

import dev.kkkkkksssssaaaa.springroadmap.member.domain.Member;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    private final MemberService memberService = new MemberService();

    @Test
    void 회원가입() {
        // given
        Member member = new Member();

        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();

        assertEquals(member.getName(), findMember.getName());
    }
}