/**
 * Created by zhangying on 6/21/18.
 * https://leetcode.com/problems/unique-paths-ii/description/
 */
public class UniquePaths2 {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        if (m == 0) return 0;
        int n = obstacleGrid[0].length;
        if (n == 0) return 0;
        if(obstacleGrid[0][0] == 1) return 0;
        int[][] map = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (obstacleGrid[i][j] > 0) map[i][j] = 0;
                else if (i == m - 1 && j == n - 1) map[i][j] = obstacleGrid[i][j] == 0 ? 1 : 0;
                else if (i == m - 1) map[i][j] = map[i][j + 1];
                else if (j == n - 1) map[i][j] = map[i + 1][j];
                else {
                    map[i][j] = map[i + 1][j] + map[i][j + 1];
                }
            }
        }
        return map[0][0];
    }
}
