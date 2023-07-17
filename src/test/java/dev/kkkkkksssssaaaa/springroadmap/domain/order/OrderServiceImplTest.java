package dev.kkkkkksssssaaaa.springroadmap.domain.order;

import dev.kkkkkksssssaaaa.springroadmap.AppConfig;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.Grade;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.Member;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    private MemberService memberService;
    private OrderService orderService;

    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();

        this.memberService = appConfig.memberService();
        this.orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member memberA = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(memberA);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        assertEquals(1000, order.getDiscountPrice());
    }
}