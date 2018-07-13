/**
 * Created by zhangying on 7/13/18.
 * https://leetcode.com/problems/maximal-rectangle/description/
 */
public class MaximalRectangle {

    /**
     * 一次通过，beats 97.51%
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        if (n == 0) return 0;
        int[][] rowLength = new int[m][n];
        int[][] columnLenght = new int[m][n];

        for (int i = 0; i < m; i++) {
            rowLength[i][n - 1] = matrix[i][n - 1] - '0';
            for (int j = n - 2; j >= 0; j--) {
                if (matrix[i][j] == '1')
                    rowLength[i][j] = rowLength[i][j + 1] + 1;
                else rowLength[i][j] = 0;
            }
        }

        for (int j = 0; j < n; j++) {
            columnLenght[m - 1][j] = matrix[m - 1][j] - '0';
            for (int i = m - 2; i >= 0; i--) {
                if (matrix[i][j] == '1')
                    columnLenght[i][j] = columnLenght[i + 1][j] + 1;
                else columnLenght[i][j] = 0;
            }
        }

//        PrintUtils printUtils = new PrintUtils();
//        printUtils.print(rowLength);
//        printUtils.print(columnLenght);

        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rowLength[i][j] > 0) {
                    int min = columnLenght[i][j];
                    if (min > result) result = min;
                    for (int k = 0; k < rowLength[i][j]; k++) {
                        if (columnLenght[i][j + k] < min) min = columnLenght[i][j + k];
                        if (min * (k + 1) > result) result = min * (k + 1);
                    }
                }
            }
        }

        return result;
    }

}
