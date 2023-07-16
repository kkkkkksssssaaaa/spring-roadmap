package dev.kkkkkksssssaaaa.springroadmap.domain.order;

public interface OrderService {

    Order createOrder(Long memberId, String itemName, int itemPrice);
}
