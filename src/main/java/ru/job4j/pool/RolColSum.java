package ru.job4j.pool;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
        Sums[] res = new Sums[matrix.length];
        Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            futures.put(i, getTask(matrix, i));
        }
        for (int i = 0; i < res.length; i++) {
            res[i] = futures.get(i).get();
        }
        return res;
    }

    private static CompletableFuture<Sums> getTask(int[][] matrix, int row) {
        return CompletableFuture.supplyAsync(() -> getSums(matrix, row));
    }

    private static Sums getSums(int[][] matrix, int row) {
        int rowRes = 0;
        int colRes = 0;
        Sums sums = new Sums();
        for (int col = 0; col < matrix.length; col++) {
            rowRes += matrix[row][col];
            colRes += matrix[col][row];
        }
        sums.setRowSum(rowRes);
        sums.setColSum(colRes);
        return sums;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[][] arr = new int[10000][10000];
        for (int[] ints : arr) {
            Arrays.fill(ints, 1);
        }
        long startSum = System.currentTimeMillis();
        RolColSum.sum(arr);
        long finishSum = System.currentTimeMillis();
        long startAsyncSum = System.currentTimeMillis();
        RolColSum.asyncSum(arr);
        long finishAsyncSum = System.currentTimeMillis();
        System.out.println("sum " + (finishSum - startSum));
        System.out.println("asyncSum " + (finishAsyncSum - startAsyncSum));
    }
}
