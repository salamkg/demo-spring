package com.example.demo.models.entities.notify;


public class Store {

    private int product = 0;

    public synchronized void produce() throws InterruptedException {
        while (product >= 1) {
            wait(); // wait until consumer gets product
        }
        product++;
        System.out.println("Produced: " + product);
        notify();
    }

    public synchronized void consume() throws InterruptedException {
        while (product < 1) {
            wait(); // wait until product will be shown
        }
        product--;
        System.out.println("Consumed: " + product);
        notify();
    }
}
