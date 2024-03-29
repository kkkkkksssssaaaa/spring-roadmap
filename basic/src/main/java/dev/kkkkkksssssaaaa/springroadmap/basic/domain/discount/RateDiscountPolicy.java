package dev.kkkkkksssssaaaa.springroadmap.basic.domain.discount;

import dev.kkkkkksssssaaaa.springroadmap.basic.annotation.MainDiscountPolicy;
import dev.kkkkkksssssaaaa.springroadmap.basic.domain.member.Grade;
import dev.kkkkkksssssaaaa.springroadmap.basic.domain.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

    @Autowired
    public RateDiscountPolicy() {
    }

    private final int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        }

        return 0;
    }
}
