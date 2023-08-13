package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.proxy.v1.abstraction;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.TraceStatus;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.logtarce.LogTrace;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.app.v1.OrderControllerV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequiredArgsConstructor
public class OrderControllerInterfaceProxy implements OrderControllerV1 {

    private final OrderControllerV1 target;
    private final LogTrace trace;

    @Override
    @GetMapping("/v1/request")
    public String request(@RequestParam("itemId") String itemId) {
        TraceStatus status = null;

        try {
            status = trace.begin("OrderController.request()");

            String result = target.request(itemId);

            trace.end(status);

            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    @Override
    @GetMapping("/v1/no-log")
    public String noLog() {
        return target.noLog();
    }
}