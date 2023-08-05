package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v5;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.callback.TraceTemplate;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.logtarce.LogTrace;
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
