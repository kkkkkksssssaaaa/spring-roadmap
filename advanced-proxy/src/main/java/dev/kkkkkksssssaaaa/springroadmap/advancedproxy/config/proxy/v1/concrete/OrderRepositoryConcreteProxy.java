package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.proxy.v1.concrete;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.TraceStatus;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.logtarce.LogTrace;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.app.v2.OrderRepositoryV2;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderRepositoryConcreteProxy extends OrderRepositoryV2 {

    private final OrderRepositoryV2 target;
    private final LogTrace trace;

    @Autowired
    public OrderRepositoryConcreteProxy(OrderRepositoryV2 target, LogTrace trace) {
        this.target = target;
        this.trace = trace;
    }

    @Override
    public void save(String itemId) {
        TraceStatus status = null;

        try {
            status = trace.begin("OrderRepository.save()");

            target.save(itemId);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
        }
    }
}
