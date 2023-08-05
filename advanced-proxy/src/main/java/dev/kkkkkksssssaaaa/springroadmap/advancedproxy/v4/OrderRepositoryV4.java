package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v4;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.logtarce.LogTrace;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

    private final LogTrace trace;

    public void save(String itemId) {

        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {

            @Override
            protected Void call() {
                if (itemId.equals("ex")) {
                    throw new IllegalStateException("예외 발생!");
                }

                sleep(1000);

                return null;
            }
        };

        template.execute("OrderRepository.save()");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
