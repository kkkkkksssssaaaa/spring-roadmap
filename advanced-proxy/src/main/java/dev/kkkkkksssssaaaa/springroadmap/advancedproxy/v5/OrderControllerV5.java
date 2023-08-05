package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v5;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.callback.TraceTemplate;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.logtarce.LogTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final TraceTemplate traceTemplate;

    @Autowired
    public OrderControllerV5(OrderServiceV5 orderService, LogTrace trace) {
        this.orderService = orderService;
        this.traceTemplate = new TraceTemplate(trace);
    }

    @GetMapping("/v5/request")
    public String request(String itemId) {
        return traceTemplate.execute(
            "OrderController.request()",
            () -> {
                orderService.orderItem(itemId);
                return "OK";
            }
        );
    }
}
