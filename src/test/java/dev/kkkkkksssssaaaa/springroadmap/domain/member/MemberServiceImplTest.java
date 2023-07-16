package dev.kkkkkksssssaaaa.springroadmap.domain.member;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceImplTest {

    private final MemberService service = new MemberServiceImpl();

    @Test
    void join() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        service.join(member);
        Member findMember = service.findMember(1L);

        // then
        assertEquals(member, findMember);
    }
}