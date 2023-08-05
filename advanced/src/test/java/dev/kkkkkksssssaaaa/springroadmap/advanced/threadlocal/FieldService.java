package dev.kkkkkksssssaaaa.springroadmap.advanced.threadlocal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldService {

    private String nameStore;

    public String logic(String name) {
        log.info("저장 name={} -> namestore={}", name, nameStore);

        nameStore = name;
        sleep(1000);

        log.info("조회 namestore={}", nameStore);

        return name;
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
