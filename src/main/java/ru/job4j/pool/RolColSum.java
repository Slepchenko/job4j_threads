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
        CompletableFuture<Sums[]> res = null;
        for (int i = 0; i < matrix.length; i++) {
            int index = i;
            res = getData(matrix, i).thenApply(sum -> {
                 Sums[] sums = new Sums[matrix.length];
                 sums[index] = sum;
                 return sums;
             });
        }
        return res.get();
    }

    private static CompletableFuture<Sums> getData(int[][] matrix, int index) {
        Sums sums = new Sums();
        return CompletableFuture.supplyAsync(() -> {
            int row = 0;
            int col = 0;
            for (int i = 0; i < matrix.length; i++) {
                row += matrix[index][i];

            }
            for (int i = 0; i < matrix.length; i++) {
                col += matrix[i][index];
            }
            sums.setRowSum(row);
            sums.setColSum(col);
            return sums;
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[][] arr = new int[10000][10000];
        for (int[] ints : arr) {
            Arrays.fill(ints, 1);
        }
        long start = System.currentTimeMillis();
        RolColSum.asyncSum(arr);
        long finish = System.currentTimeMillis();
        System.out.println(finish - start);
    }
}
