package dev.kkkkkksssssaaaa.springroadmap;

import dev.kkkkkksssssaaaa.springroadmap.domain.discount.DiscountPolicy;
import dev.kkkkkksssssaaaa.springroadmap.domain.discount.RateDiscountPolicy;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemberRepository;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemberService;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemberServiceImpl;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemoryMemberRepository;
import dev.kkkkkksssssaaaa.springroadmap.domain.order.OrderService;
import dev.kkkkkksssssaaaa.springroadmap.domain.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(repository());
    }

    public MemberRepository repository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(
            repository(),
            discountPolicy()
        );
    }
}
