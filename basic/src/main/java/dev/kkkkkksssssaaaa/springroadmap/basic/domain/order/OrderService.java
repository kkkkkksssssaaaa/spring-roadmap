package dev.kkkkkksssssaaaa.springroadmap.basic.domain.order;

public interface OrderService {

    Order createOrder(Long memberId, String itemName, int itemPrice);
}
