import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangying on 6/14/18.
 * https://leetcode.com/problems/n-queens/description/
 */
public class NQueen {
    List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        boolean[][] map = new boolean[n][n];
        queue(map, 0, n);
        return result;
    }

    private boolean queue(boolean[][] map, int row, int n) {
        if (row == n) {
            List<String> resultItem = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (map[i][j]) sb.append("Q");
                    else sb.append(".");
                }
                resultItem.add(sb.toString());
            }
            result.add(resultItem);
            return true;
        }
        for (int i = 0; i < n; i++) {
            if (isValid(map, row, i, n)) {
                map[row][i] = true;
                queue(map, row + 1, n);
                map[row][i] = false;
            }
        }
        return true;
    }

    private boolean isValid(boolean[][] map, int row, int column, int n) {
        for (int i = 1; i <= row && i <= column; i++) {
            if (map[row - i][column - i]) return false;
        }
        for (int i = 1; i <= row && i + column < n; i++) {
            if (map[row - i][column + i]) return false;
        }
        for (int i = 0; i < column; i++) {
            if (map[row][i]) return false;
        }
        for (int i = 0; i < row; i++) {
            if (map[i][column]) return false;
        }
        return true;
    }

}
