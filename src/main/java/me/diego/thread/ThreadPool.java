package me.diego.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    private final ExecutorService executor;
    private final Integer quantity;

    public ThreadPool(Integer quantity) {
        this.quantity = quantity;
        this.executor = Executors.newFixedThreadPool(quantity);
    }

    public void runThread(Runnable runnable) {
        for (int i = 0; i < quantity; i++) {
            executor.submit(runnable);
        }
        executor.shutdown();
    }
}
