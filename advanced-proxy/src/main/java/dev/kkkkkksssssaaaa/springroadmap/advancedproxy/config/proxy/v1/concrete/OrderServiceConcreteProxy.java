package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.proxy.v1.concrete;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.TraceStatus;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.logtarce.LogTrace;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v2.OrderServiceV2;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceConcreteProxy extends OrderServiceV2 {

    private final OrderServiceV2 target;
    private final LogTrace trace;

    @Autowired
    public OrderServiceConcreteProxy(
        OrderServiceV2 target,
        LogTrace trace
    ) {
        // 부모 클래스를 초기화하지 않고 프록시로 사용할 것이기 때문에 null binding
        super(null);

        this.target = target;
        this.trace = trace;
    }

    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;

        try {
            status = trace.begin("OrderService.orderItem()");

            target.orderItem(itemId);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
        }
    }
}
