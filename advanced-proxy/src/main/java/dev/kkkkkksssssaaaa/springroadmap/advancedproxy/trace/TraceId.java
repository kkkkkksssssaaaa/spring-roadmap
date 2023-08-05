package dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace;

import java.util.UUID;

/**
 * HTTP Request 의 트랜잭션에 대한 ID 와 요청이 수행될 때 실행되는 메서드의 깊이를 표헌하는 클리스
 *
 * example)
 * [796bccd9] OrderController.request()
 * [796bccd9] |-->OrderService.orderItem()
 * [796bccd9] |  |-->OrderRepository.save()
 * */
public class TraceId {

    private String id;
    private int level;

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    public TraceId createNextId() {
        return new TraceId(id, level + 1);
    }

    public TraceId createPreviousId() {
        return new TraceId(id, level - 1);
    }

    public boolean isFirstLevel() {
        return level == 0;
    }

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    private String createId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
