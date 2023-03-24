package ru.job4j.pool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

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

    public static Sums[] asyncSum(Integer[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] result = new Sums[matrix.length];
//        CompletableFuture<Integer[]> res = getRow(matrix).thenApply(getColumn(matrix), (r, c) -> r);
        for (int i = 0; i < matrix.length; i++) {
            result[i].setRowSum(getRow(matrix).get()[i]);
            result[i].setColSum(getColumn(matrix).get()[i]);
        }
        return result;
    }


    private static CompletableFuture<Integer[]> getRow(Integer[][] data) {
        return CompletableFuture.supplyAsync(() -> {
            Integer[] res = new Integer[data.length];
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    res[i] += data[i][j];
                }
            }
            return res;
        });
    }

    private static CompletableFuture<Integer[]> getColumn(Integer[][] data) {
        return CompletableFuture.supplyAsync(() -> {
            Integer[] res = new Integer[data.length];
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    res[i] = res[i] + data[i][j];
                }
            }
            return res;
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Integer[][] arr = new Integer[][]{{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};
//        RolColSum.Sums[] res = RolColSum.sum(arr);
//        for (Sums re : res) {
//            System.out.println(re.getRowSum() + " " + re.getColSum());
//        }
        RolColSum.Sums[] res = RolColSum.asyncSum(arr);
        for (Sums re : res) {
            System.out.println(re.getRowSum() + " " + re.getColSum());
        }
    }
}
