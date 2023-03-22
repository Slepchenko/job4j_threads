package ru.job4j.pool;

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
        Sums[] res = new Sums[3];
        int row = 0;
        int col = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

            }
        }

        return res;
    }

    public static Sums[] asyncSum(int[][] matrix) {
        return null;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1, 2, 3},
                                  {4, 5, 6},
                                  {7, 8, 9}};
        RolColSum.Sums[] res = RolColSum.sum(arr);
        for (Sums re : res) {
            System.out.println(re.getRowSum() + " " + re.getColSum());
        }
    }
}
