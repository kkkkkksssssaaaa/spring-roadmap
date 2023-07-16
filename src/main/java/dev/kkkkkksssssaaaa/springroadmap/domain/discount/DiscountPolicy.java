package dev.kkkkkksssssaaaa.springroadmap.domain.discount;

import dev.kkkkkksssssaaaa.springroadmap.domain.member.Member;

public interface DiscountPolicy {

    /**
     * @return 할인 대상 금액
     * */
    int discount(Member member, int price);
}
