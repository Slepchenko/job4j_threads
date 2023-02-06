package ru.job4j.nonblockingalgoritm;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {

    private final AtomicReference<Integer> count = new AtomicReference<>();

    private synchronized void in() {
        if (count.get() == null) {
            count.set(0);

        }
    }

    public void increment() {
        in();
        int curValue;
        int newValue;
        do {
            curValue = count.get();
            newValue = curValue + 1;
        } while (!count.compareAndSet(curValue, newValue));
    }

    public int get() {
        return count.get();
    }
}
