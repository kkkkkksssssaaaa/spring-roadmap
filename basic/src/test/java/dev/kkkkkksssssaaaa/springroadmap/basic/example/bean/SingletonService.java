package dev.kkkkkksssssaaaa.springroadmap.basic.example.bean;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    private SingletonService() {
    }

    public static SingletonService getInstance() {
        return instance;
    }

    public void logic() {
        System.out.println("싱글톤 패턴을 적용한 객체 사용");
    }
}
