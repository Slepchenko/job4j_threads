package ru.job4j.pool;

import java.util.concurrent.RecursiveTask;

public class ParallelFindIndex<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final int from;
    private final int to;
    private final T obj;

    public ParallelFindIndex(T obj, T[] array, int from, int to) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.obj = obj;
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            return linearSearchElement();
        }

        int mid = (from + to) / 2;
        ParallelFindIndex<T> left = new ParallelFindIndex<>(obj, array, from, mid);
        ParallelFindIndex<T> right = new ParallelFindIndex<>(obj, array, mid + 1, to);
        left.fork();
        right.fork();
        int l = left.join();
        int r = right.join();
        return Math.max(l, r);
    }

    private int linearSearchElement() {
        for (int i = from; i <= to; i++) {
            if (array[i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }
}