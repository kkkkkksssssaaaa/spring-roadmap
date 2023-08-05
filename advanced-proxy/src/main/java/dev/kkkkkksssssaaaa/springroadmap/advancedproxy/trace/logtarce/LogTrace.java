package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.logtarce;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);
}
