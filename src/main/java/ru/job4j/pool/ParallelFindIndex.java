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
        if (from == to) {
            return from;
        }
        int mid = (from + to) / 2;
        ParallelFindIndex<T> left = new ParallelFindIndex<>(obj, array, from, mid);
        ParallelFindIndex<T> right = new ParallelFindIndex<>(obj, array, mid + 1, to);
        left.fork();
        right.fork();
        return result(left.join(), right.join());
    }

    private int result(int l, int r) {
        if (array[r].equals(obj)) {
            return r;
        }
        return l;
    }
}
