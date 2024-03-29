package dev.kkkkkksssssaaaa.springroadmap.advancedaop.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {

    private final OrderRepository repository;

    @Autowired
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public void orderItem(String itemId) {
        log.info("[orderService] 실행");

        repository.save(itemId);
    }
}
