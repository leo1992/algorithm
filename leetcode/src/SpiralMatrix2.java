/**
 * Created by zhangying on 6/18/18.
 * https://leetcode.com/problems/spiral-matrix-ii/description/
 */
public class SpiralMatrix2 {

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int total = n * n;
        for (int margin = 0, number = 1; number <= total; margin++) {
            for (int i = margin; i < n - margin; i++, number++) result[margin][i] = number;
            for (int i = margin + 1; i < n - margin && number <= total; i++, number++)
                result[i][n - 1 - margin] = number;
            for (int i = margin + 1; i < n - margin && number <= total; i++, number++)
                result[n - 1 - margin][n - 1 - i] = number;
            for (int i = margin + 1; i < n - margin - 1 && number <= total; i++, number++)
                result[n - 1 - i][margin] = number;
        }
        return result;
    }
}

