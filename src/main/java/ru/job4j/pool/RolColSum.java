package ru.job4j.pool;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static Sums[] sum(int[][] matrix) {
        Sums[] res = new Sums[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            res[i] = new Sums();
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                res[i].setRowSum(res[i].getRowSum() + matrix[i][j]);
                res[j].setColSum(res[j].getColSum() + matrix[i][j]);
            }
        }
        return res;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        CompletableFuture<Sums[]> res = getRow(matrix).thenCombine(getColumn(matrix), (r, c) -> {
            Sums[] sums = new Sums[matrix.length];
            for (int i = 0; i < sums.length; i++) {
                Sums s = new Sums();
                s.setRowSum(r[i]);
                s.setColSum(c[i]);
                sums[i] = s;
            }
            return sums;
        });
        return res.get();
    }

    private static CompletableFuture<Sums> getData(int[][] matrix, int index) {
        Sums sums = new Sums();

        return CompletableFuture.supplyAsync(() -> {
            int row = 0;
            int col = 0;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (i == index) {
                        row += matrix[i][j];
                    }
                    if (j == index) {
                        col += matrix[i][j];
                    }
                }
            }
            sums.setRowSum(row);
            sums.setColSum(col);
            return sums;
        });
    }

    private static CompletableFuture<int[]> getRow(int[][] data) {
        return CompletableFuture.supplyAsync(() -> {
            int[] res = new int[data.length];
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    res[i] += data[i][j];
                }
            }
            return res;
        });
    }

    private static CompletableFuture<int[]> getColumn(int[][] data) {
        return CompletableFuture.supplyAsync(() -> {
            int[] res = new int[data.length];
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    res[j] += data[i][j];
                }
            }
            return res;
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        int[][] arr = new int[10000][10000];
//        for (int[] ints : arr) {
//            Arrays.fill(ints, 1);
//        }
//        long start = System.currentTimeMillis();
//        RolColSum.asyncSum(arr);
//        long finish = System.currentTimeMillis();
//        System.out.println(finish - start);

        int[][] arr = new int[][]{{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};

        System.out.println(getData(arr, 1).get().getRowSum() + " " + getData(arr, 1).get().getColSum());
    }
}
