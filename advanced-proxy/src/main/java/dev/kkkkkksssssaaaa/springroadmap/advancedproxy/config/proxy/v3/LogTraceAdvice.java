package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.proxy.v3;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.TraceStatus;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.logtarce.LogTrace;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;

public class LogTraceAdvice implements MethodInterceptor {

    private final LogTrace trace;

    @Autowired
    public LogTraceAdvice(LogTrace trace) {
        this.trace = trace;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        TraceStatus status = null;

        try {
            Method method = invocation.getMethod();

            String message =
                method.getDeclaringClass().getSimpleName()
                    + "."
                    + method.getName()
                    + "()";

            status = trace.begin(message);

            Object result = invocation.proceed();

            trace.end(status);

            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
