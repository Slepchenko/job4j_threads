package ru.job4j.pool;

import ru.job4j.blockingqueue.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(2);

    public ThreadPool() {
        Thread thread;
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            thread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        tasks.poll().run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                }
            });
            thread.start();
            threads.add(thread);
        }
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        for (Thread th : threads) {
            th.interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        threadPool.work(() -> System.out.println("Thread 1"));
        threadPool.work(() -> System.out.println("Thread 2"));
        threadPool.work(() -> System.out.println("Thread 3"));
        threadPool.work(() -> System.out.println("Thread 4"));
        threadPool.work(() -> System.out.println("Thread 5"));
        threadPool.work(() -> System.out.println("Thread 6"));
        threadPool.work(() -> System.out.println("Thread 7"));
        threadPool.work(() -> System.out.println("Thread 8"));
        threadPool.shutdown();
    }
}
