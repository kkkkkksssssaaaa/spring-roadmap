package dev.kkkkkksssssaaaa.springroadmap.advancedaop.exam;

import dev.kkkkkksssssaaaa.springroadmap.advancedaop.exam.annotation.Trace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository repository;

    @Trace
    public void request(String itemId) {
        repository.save(itemId);
    }
}
