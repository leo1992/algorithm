/**
 * Created by zhangying on 7/6/18.
 * https://leetcode.com/problems/word-search/description/
 */
public class WordSearch {

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        if (m == 0) return false;
        int n = board[0].length;
        boolean[][] map = new boolean[m][n];
        char[] wordChar = word.toCharArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == wordChar[0]) {
                    map[i][j] = true;
                    if (startSearch(board, map, wordChar, m, n, i, j, 1))
                        return true;
                    map[i][j] = false;
                }
            }
        }
        return false;
    }

    private boolean startSearch(char[][] board, boolean[][] map, char[] target, int m, int n, int i, int j, int index) {
        if (index >= target.length) return true;
        if (i + 1 < m && !map[i + 1][j] && board[i + 1][j] == target[index]) {
            map[i + 1][j] = true;
            if (startSearch(board, map, target, m, n, i + 1, j, index + 1)) return true;
            map[i + 1][j] = false;
        }
        if (i - 1 >= 0 && !map[i - 1][j] && board[i - 1][j] == target[index]) {
            map[i - 1][j] = true;
            if (startSearch(board, map, target, m, n, i - 1, j, index + 1)) return true;
            map[i - 1][j] = false;

        }
        if (j + 1 < n && !map[i][j + 1] && board[i][j + 1] == target[index]) {
            map[i][j + 1] = true;
            if (startSearch(board, map, target, m, n, i, j + 1, index + 1)) return true;
            map[i][j + 1] = false;

        }
        if (j - 1 >= 0 && !map[i][j - 1] && board[i][j - 1] == target[index]) {
            map[i][j - 1] = true;
            if (startSearch(board, map, target, m, n, i, j - 1, index + 1)) return true;
            map[i][j - 1] = false;

        }
        return false;
    }

}
