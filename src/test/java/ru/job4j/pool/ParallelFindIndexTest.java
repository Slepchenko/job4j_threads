package ru.job4j.pool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.*;

class ParallelFindIndexTest {

    @Test
    public void whenFoundIndexInteger() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Integer[] array = new Integer[]{100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110};
        ParallelFindIndex<Integer> pfi = new ParallelFindIndex<>(103, array, 0, array.length - 1);
        assertEquals(3, forkJoinPool.invoke(pfi));
    }

    @Test
    public void whenFoundIndexString() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        String[] array = new String[]{"100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "110"};
        ParallelFindIndex<String> pfi = new ParallelFindIndex<>("103", array, 0, array.length - 1);
        assertEquals(3, forkJoinPool.invoke(pfi));
    }

    @Test
    public void whenNotFoundElement() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Integer[] array = new Integer[]{100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111};
        ParallelFindIndex<Integer> pfi = new ParallelFindIndex<>(112, array, 0, array.length - 1);
        assertEquals(-1, forkJoinPool.invoke(pfi));
    }

}