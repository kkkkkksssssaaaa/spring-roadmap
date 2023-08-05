package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v5;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.callback.TraceTemplate;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.logtarce.LogTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV5 {

    private final TraceTemplate traceTemplate;

    @Autowired
    public OrderRepositoryV5(LogTrace trace) {
        this.traceTemplate = new TraceTemplate(trace);
    }

    public void save(String itemId) {
        traceTemplate.execute(
            "OrderRepository.save()",
            () -> {
                if (itemId.equals("ex")) {
                    throw new IllegalStateException("예외 발생!");
                }

                sleep(1000);

                return null;
            }
        );
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
