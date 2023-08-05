package dev.kkkkkksssssaaaa.springroadmap.advanced.v5;

import dev.kkkkkksssssaaaa.springroadmap.advanced.trace.callback.TraceTemplate;
import dev.kkkkkksssssaaaa.springroadmap.advanced.trace.logtarce.LogTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepository;
    private final TraceTemplate traceTemplate;

    @Autowired
    public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace trace) {
        this.orderRepository = orderRepository;
        this.traceTemplate = new TraceTemplate(trace);
    }

    public void orderItem(String itemId) {
        traceTemplate.execute(
            "OrderService.orderItem()",
            () -> {
                orderRepository.save(itemId);
                return null;
            }
        );
    }
}
