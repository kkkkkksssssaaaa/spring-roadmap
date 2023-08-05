package dev.kkkkkksssssaaaa.springroadmap.advanced.v1;

import dev.kkkkkksssssaaaa.springroadmap.advanced.trace.TraceStatus;
import dev.kkkkkksssssaaaa.springroadmap.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final OrderServiceV1 orderService;
    private final HelloTraceV1 trace;

    @GetMapping("/v1/request")
    public String request(String itemId) {
        TraceStatus status = null;

        try {
            status = trace.begin("OrderController.request()");

            orderService.orderItem(itemId);

            trace.end(status);

            return "OK";
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
