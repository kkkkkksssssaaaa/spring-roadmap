package dev.kkkkkksssssaaaa.springroadmap;

import dev.kkkkkksssssaaaa.springroadmap.domain.discount.FixDiscountPolicy;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemberService;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemberServiceImpl;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemoryMemberRepository;
import dev.kkkkkksssssaaaa.springroadmap.domain.order.OrderService;
import dev.kkkkkksssssaaaa.springroadmap.domain.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(
            new MemoryMemberRepository(),
            new FixDiscountPolicy()
        );
    }
}
