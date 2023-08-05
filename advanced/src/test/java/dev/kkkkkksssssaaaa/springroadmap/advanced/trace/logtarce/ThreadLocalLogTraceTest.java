package dev.kkkkkksssssaaaa.springroadmap.advanced.trace.logtarce;

import dev.kkkkkksssssaaaa.springroadmap.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class ThreadLocalLogTraceTest {

    private ThreadLocalLogTrace trace = new ThreadLocalLogTrace();

    @Test
    void beginAndEndLevel2() {
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");

        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void beginAndExceptionLevel2() {
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");

        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }
}
