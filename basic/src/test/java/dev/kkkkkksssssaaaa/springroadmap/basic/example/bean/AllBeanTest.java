package dev.kkkkkksssssaaaa.springroadmap.basic.example.bean;

import dev.kkkkkksssssaaaa.springroadmap.basic.AutoAppConfig;
import dev.kkkkkksssssaaaa.springroadmap.basic.domain.discount.DiscountPolicy;
import dev.kkkkkksssssaaaa.springroadmap.basic.domain.member.Grade;
import dev.kkkkkksssssaaaa.springroadmap.basic.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class AllBeanTest {

    @Test
    void findAllBean() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = context.getBean(DiscountService.class);

        Member member = new Member(1L, "member", Grade.VIP);
        int discountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");

        assertInstanceOf(DiscountService.class, discountService);
        assertEquals(discountPrice, 2000);
    }

    static class DiscountService {

        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policyList;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
            this.policyMap = policyMap;
            this.policyList = policyList;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policyList = " + policyList);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
