package com.example.demo.models.entities.notify;

import java.util.LinkedList;
import java.util.Queue;

public class TaskQueue {
    private final Queue<String> queue = new LinkedList<>();

    public synchronized void addTask(String task) {
        queue.offer(task);
        System.out.println("Task added: " + task);
        notify();
    }

    public synchronized String getTask() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        return queue.poll();
    }
}
