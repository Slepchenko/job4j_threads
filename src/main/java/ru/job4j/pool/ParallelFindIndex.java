package ru.job4j.pool;

import java.util.concurrent.RecursiveTask;

public class ParallelFindIndex<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final int from;
    private final int to;
    private final T t;

    public ParallelFindIndex(T t, T[] array, int from, int to) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.t = t;
    }

    @Override
    protected Integer compute() {
        if (from == to) {
            if (t.equals(array[from])) {
                return from;
            }
        }
        int mid = (from + to) / 2;

        ParallelFindIndex<T> leftFind = new ParallelFindIndex<>(t, array, from, mid);
        ParallelFindIndex<T> rightFind = new ParallelFindIndex<>(t, array, mid + 1, to);
        leftFind.fork();
        rightFind.fork();
        leftFind.join();
        rightFind.join();
        return null;
    }
}
