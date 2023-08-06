package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v1;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.ExcludeComponent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@ExcludeComponent
@Controller
@ResponseBody
public interface OrderControllerV1 {

    @GetMapping("/v1/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v1/no-log")
    String noLog();
}
