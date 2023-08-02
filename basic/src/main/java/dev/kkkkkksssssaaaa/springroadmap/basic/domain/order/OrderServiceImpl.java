package dev.kkkkkksssssaaaa.springroadmap.basic.domain.order;

import dev.kkkkkksssssaaaa.springroadmap.basic.annotation.MainDiscountPolicy;
import dev.kkkkkksssssaaaa.springroadmap.basic.domain.discount.DiscountPolicy;
import dev.kkkkkksssssaaaa.springroadmap.basic.domain.member.MemberRepository;
import dev.kkkkkksssssaaaa.springroadmap.basic.domain.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository repository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(
        MemberRepository repository,
        @MainDiscountPolicy DiscountPolicy discountPolicy
    ) {
        this.repository = repository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = repository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getRepository() {
        return repository;
    }
}
