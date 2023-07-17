package dev.kkkkkksssssaaaa.springroadmap.domain.order;

import dev.kkkkkksssssaaaa.springroadmap.domain.discount.DiscountPolicy;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.Member;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository repository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository repository, DiscountPolicy discountPolicy) {
        this.repository = repository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = repository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
