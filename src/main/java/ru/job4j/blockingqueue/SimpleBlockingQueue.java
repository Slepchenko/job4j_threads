package ru.job4j.blockingqueue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    private final int size;

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    public synchronized void offer(T value) {
        try {
            while (queue.size() >= size) {
                this.wait();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        this.notify();
        queue.offer(value);
    }

    public synchronized T poll() {
        try {
            while (queue.size() == 0) {
                this.wait();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.notify();
        return queue.poll();
    }
}