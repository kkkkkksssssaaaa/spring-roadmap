package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.template;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.TraceStatus;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.logtarce.LogTrace;

public abstract class AbstractTemplate<T> {

    private final LogTrace trace;

    public AbstractTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public T execute(String message) {
        TraceStatus status = null;

        try {
            status = trace.begin(message);

            T result = call();

            trace.end(status);

            return result;
        } catch (Exception e) {
            trace.exception(status, e);

            throw e;
        }
    }

    protected abstract T call();
}
