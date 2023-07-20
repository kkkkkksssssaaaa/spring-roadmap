package dev.kkkkkksssssaaaa.springroadmap;

import dev.kkkkkksssssaaaa.springroadmap.domain.discount.FixDiscountPolicy;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemberService;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemberServiceImpl;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemoryMemberRepository;
import dev.kkkkkksssssaaaa.springroadmap.domain.order.OrderService;
import dev.kkkkkksssssaaaa.springroadmap.domain.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(repository());
    }

    public MemoryMemberRepository repository() {
        return new MemoryMemberRepository();
    }

    public FixDiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(
            repository(),
            discountPolicy()
        );
    }
}
