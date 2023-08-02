package dev.kkkkkksssssaaaa.springroadmap.basic.domain.discount;

import dev.kkkkkksssssaaaa.springroadmap.basic.domain.discount.RateDiscountPolicy;
import dev.kkkkkksssssaaaa.springroadmap.basic.domain.member.Grade;
import dev.kkkkkksssssaaaa.springroadmap.basic.domain.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    private final RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP 는 10% 할인이 적용 되어야 한다")
    void vip_o() {
        // given
        Member vip = new Member(1L, "memberVIP", Grade.VIP);

        // when
        int discount = rateDiscountPolicy.discount(vip, 10000);

        // then
        assertEquals(discount, 1000);
    }

    @Test
    @DisplayName("VIP 가 아니면 할인이 적용되지 않아야 한다")
    void vip_x() {
        // given
        Member basic = new Member(1L, "memberVIP", Grade.BASIC);

        // when
        int discount = rateDiscountPolicy.discount(basic, 10000);

        // then
        assertEquals(discount, 0);
    }
}