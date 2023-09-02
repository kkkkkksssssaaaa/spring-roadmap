package dev.kkkkkksssssaaaa.springroadmap.advancedaop.exam;

import org.springframework.stereotype.Repository;

@Repository
public class ExamRepository {

    private static int seq = 0;

    public String save(String itemid) {
        seq++;

        if (seq % 5 == 0) {
            throw new IllegalStateException("예외 발생");
        }

        return "ok";
    }
}
