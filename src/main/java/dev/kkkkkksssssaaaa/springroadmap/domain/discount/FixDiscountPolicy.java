package dev.kkkkkksssssaaaa.springroadmap.domain.discount;

import dev.kkkkkksssssaaaa.springroadmap.domain.member.Grade;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        }

        return 0;
    }
}
