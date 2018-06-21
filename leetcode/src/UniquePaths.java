/**
 * Created by zhangying on 6/21/18.
 * https://leetcode.com/problems/unique-paths/description/
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) return 1;
        int[][] map = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) map[i][j] = 0;
                else if (i == m - 1) map[i][j] = 1;
                else if (j == n - 1) map[i][j] = 1;
                else {
                    map[i][j] = map[i + 1][j] + map[i][j + 1];
                }
            }
        }
        return map[0][0];
    }

}
