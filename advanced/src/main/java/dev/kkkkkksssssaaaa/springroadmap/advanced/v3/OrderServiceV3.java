package dev.kkkkkksssssaaaa.springroadmap.advanced.v3;

import dev.kkkkkksssssaaaa.springroadmap.advanced.trace.TraceStatus;
import dev.kkkkkksssssaaaa.springroadmap.advanced.trace.logtarce.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId) {
        TraceStatus status = null;

        try {
            status = trace.begin("OrderService.orderItem()");

            orderRepository.save(itemId);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
