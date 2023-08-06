package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v2;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.ExcludeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ExcludeComponent
@Controller
@ResponseBody
public class OrderControllerV2 {

    private final OrderServiceV2 service;

    public OrderControllerV2(OrderServiceV2 service) {
        this.service = service;
    }

    @GetMapping("/v2/request")
    public String request(String itemId) {
        service.orderItem(itemId);

        return "OK";
    }

    @GetMapping("/v2/no-log")
    public String noLog() {
        return "OK";
    }
}
