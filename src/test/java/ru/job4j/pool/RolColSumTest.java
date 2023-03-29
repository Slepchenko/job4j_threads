package ru.job4j.pool;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

class RolColSumTest {

    @Test
    public void whenSumTrue() {
        int[][] matrix = new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        Sums[] expect = new Sums[]{new Sums(), new Sums(), new Sums()};
        for (int i = 0; i < expect.length; i++) {
            expect[i].setColSum(3);
            expect[i].setRowSum(3);
        }
        Sums[] result = RolColSum.sum(matrix);
        Assertions.assertArrayEquals(expect, result);
    }

    @Test
    public void whenAsyncSumTrue() throws ExecutionException, InterruptedException {
        int[][] matrix = new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        Sums[] expect = new Sums[]{new Sums(), new Sums(), new Sums()};
        for (int i = 0; i < expect.length; i++) {
            expect[i].setColSum(3);
            expect[i].setRowSum(3);
        }
        Sums[] result = RolColSum.asyncSum(matrix);
        Assertions.assertArrayEquals(expect, result);
    }
}