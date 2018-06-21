/**
 * Created by zhangying on 6/21/18.
 * https://leetcode.com/problems/minimum-path-sum/description/
 */
public class PathSum {

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;
        if (n == 0) return 0;
        int[][] calMap = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) calMap[i][j] = grid[i][j];
                else if (i == 0) calMap[i][j] = grid[i][j] + calMap[i][j - 1];
                else if (j == 0) calMap[i][j] = grid[i][j] + calMap[i - 1][j];
                else {
                    calMap[i][j] = grid[i][j] + (calMap[i - 1][j] > calMap[i][j - 1] ? calMap[i][j - 1] : calMap[i - 1][j]);
                }
            }
        }
        return calMap[m - 1][n - 1];
    }

}
