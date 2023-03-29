package ru.job4j.pool;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        Assertions.assertArrayEquals(result, expect);
    }


}