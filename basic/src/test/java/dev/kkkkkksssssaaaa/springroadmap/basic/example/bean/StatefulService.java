package dev.kkkkkksssssaaaa.springroadmap.basic.example.bean;

public class StatefulService {

    private int price;

    public void order(String name, int price) {
        System.out.println("name = " + name);
        System.out.println("price = " + price);

        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }
}
