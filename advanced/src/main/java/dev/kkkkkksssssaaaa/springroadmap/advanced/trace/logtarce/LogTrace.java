package dev.kkkkkksssssaaaa.springroadmap.advanced.trace.logtarce;

import dev.kkkkkksssssaaaa.springroadmap.advanced.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);
}
