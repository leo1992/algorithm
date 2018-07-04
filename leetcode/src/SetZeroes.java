/**
 * Created by zhangying on 6/25/18.
 * https://leetcode.com/problems/set-matrix-zeroes/description/
 */
public class SetZeroes {

    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return;
        int n = matrix[0].length;
        boolean[] rows = new boolean[m];
        boolean[] columns = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    columns[j] = true;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            if (rows[i]) {
                for (int j = 0; j < n; j++) matrix[i][j] = 0;
            }
        }

        for (int j = 0; j < n; j++) {
            if (rows[j]) {
                for (int i = 0; i < m; i++) matrix[i][j] = 0;
            }
        }
    }

    public void setZeroesBetter(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return;
        int n = matrix[0].length;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < n; j++) matrix[i][j] = 0;
            }
        }

        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < m; i++) matrix[i][j] = 0;
            }
        }
    }

}
