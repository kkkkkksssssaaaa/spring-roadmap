package dev.kkkkkksssssaaaa.springroadmap.advanced.v2;

import dev.kkkkkksssssaaaa.springroadmap.advanced.trace.TraceId;
import dev.kkkkkksssssaaaa.springroadmap.advanced.trace.TraceStatus;
import dev.kkkkkksssssaaaa.springroadmap.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final HelloTraceV2 trace;

    public void save(TraceId traceId, String itemId) {
        TraceStatus status = null;

        try {
            status = trace.beginSync(traceId, "OrderRepository.save()");

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
