package dev.kkkkkksssssaaaa.springroadmap;

import dev.kkkkkksssssaaaa.springroadmap.domain.discount.DiscountPolicy;
import dev.kkkkkksssssaaaa.springroadmap.domain.discount.RateDiscountPolicy;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemberRepository;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemberService;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemberServiceImpl;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemoryMemberRepository;
import dev.kkkkkksssssaaaa.springroadmap.domain.order.OrderService;
import dev.kkkkkksssssaaaa.springroadmap.domain.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
            memberRepository(),
            discountPolicy()
        );
    }
}
