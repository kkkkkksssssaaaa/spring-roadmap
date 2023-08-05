package dev.kkkkkksssssaaaa.springroadmap.advanced.v1;

import dev.kkkkkksssssaaaa.springroadmap.advanced.trace.TraceStatus;
import dev.kkkkkksssssaaaa.springroadmap.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

    private final HelloTraceV1 trace;

    public void save(String itemId) {
        TraceStatus status = null;

        try {
            status = trace.begin("OrderRepository.save()");

            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
