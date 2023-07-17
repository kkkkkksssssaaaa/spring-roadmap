package dev.kkkkkksssssaaaa.springroadmap.domain.member;

import dev.kkkkkksssssaaaa.springroadmap.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceImplTest {

    private MemberService service;

    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();

        this.service = appConfig.memberService();
    }

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