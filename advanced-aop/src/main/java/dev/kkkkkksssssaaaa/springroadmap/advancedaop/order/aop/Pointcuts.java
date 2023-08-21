package dev.kkkkkksssssaaaa.springroadmap.advancedaop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("allOrder() && allService()")
    public void orderAndService() {}

    @Pointcut("execution(* dev.kkkkkksssssaaaa.springroadmap.advancedaop.order..*(..))")
    public void allOrder() {}

    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {}
}
