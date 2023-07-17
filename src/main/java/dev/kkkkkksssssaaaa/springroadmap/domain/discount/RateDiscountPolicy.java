package dev.kkkkkksssssaaaa.springroadmap.domain.discount;

import dev.kkkkkksssssaaaa.springroadmap.domain.member.Grade;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.Member;

public class RateDiscountPolicy implements DiscountPolicy {

    private final int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        }

        return 0;
    }
}
