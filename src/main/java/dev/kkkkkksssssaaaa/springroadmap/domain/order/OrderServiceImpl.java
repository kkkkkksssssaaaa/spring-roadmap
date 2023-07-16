package dev.kkkkkksssssaaaa.springroadmap.domain.order;

import dev.kkkkkksssssaaaa.springroadmap.domain.discount.DiscountPolicy;
import dev.kkkkkksssssaaaa.springroadmap.domain.discount.FixDiscountPolicy;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.Member;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemberRepository;
import dev.kkkkkksssssaaaa.springroadmap.domain.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository repository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = repository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
