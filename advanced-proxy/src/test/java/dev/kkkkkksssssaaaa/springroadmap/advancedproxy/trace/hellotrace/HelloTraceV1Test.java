package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.advancedproxy.hellotrace;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloTraceV1Test {

    @Test
    void beginAndEnd() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");

        trace.end(status);
    }

    @Test
    void beginAndException() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.exception(status, new IllegalStateException());
    }
}