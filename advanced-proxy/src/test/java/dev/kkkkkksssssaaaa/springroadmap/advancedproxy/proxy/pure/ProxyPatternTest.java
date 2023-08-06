package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.pure;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.pure.code.CacheProxy;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.pure.code.ProxyPatternClient;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.pure.code.RealSubject;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.proxy.pure.code.Subject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    void noProxyTest() {
        Subject subject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(subject);

        client.execute();
        client.execute();
        client.execute();
    }

    @Test
    void cacheProxyTest() {
        Subject subject = new RealSubject();
        Subject proxySubject = new CacheProxy(subject);
        ProxyPatternClient client = new ProxyPatternClient(proxySubject);

        client.execute();
        client.execute();
        client.execute();
    }
}
