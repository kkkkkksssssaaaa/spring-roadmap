package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.advancedproxy.hellotrace;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloTraceV2Test {

    @Test
    void beginAndEnd() {
        HelloTraceV2 trace = new HelloTraceV2();

        TraceStatus status = trace.begin("hello");
        TraceStatus syncedStatus = trace.beginSync(status.getTraceId(), "hello2");

        trace.end(status);
        trace.end(syncedStatus);
    }

    @Test
    void beginAndException() {
        HelloTraceV2 trace = new HelloTraceV2();

        TraceStatus status = trace.begin("hello");
        TraceStatus syncedStatus = trace.beginSync(status.getTraceId(), "hello2");

        trace.exception(status, new IllegalStateException());
        trace.exception(syncedStatus, new IllegalStateException());
    }
}