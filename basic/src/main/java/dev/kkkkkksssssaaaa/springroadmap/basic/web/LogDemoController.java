package dev.kkkkkksssssaaaa.springroadmap.basic.web;

import dev.kkkkkksssssaaaa.springroadmap.basic.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger logger;

    @RequestMapping("/log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        logger.setRequestUrl(request.getRequestURI());

        logger.log("controller test");
        logDemoService.logic("testId");

        return "OK";
    }
}
