package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v2;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.TraceStatus;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;
    private final HelloTraceV2 trace;

    @GetMapping("/v2/request")
    public String request(String itemId) {
        TraceStatus status = null;

        try {
            status = trace.begin("OrderController.request()");

            orderService.orderItem(status.getTraceId(), itemId);

            trace.end(status);

            return "OK";
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
