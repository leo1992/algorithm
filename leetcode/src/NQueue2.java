/**
 * Created by zhangying on 6/14/18.
 * https://leetcode.com/problems/n-queens-ii/description/
 */
public class NQueue2 {

    private int count = 0;

    public int totalNQueens(int n) {
        boolean[][] map = new boolean[n][n];
        queue(map, 0, n);
        return count;
    }

    private boolean queue(boolean[][] map, int row, int n) {
        if (row == n) {
            count ++;
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

    /**
     * leetCode上普遍的算法
     * @param n
     * @return
     */
    public int totalNQueensCommon(int n) {
        // 规定
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        boolean[] vis = new boolean[n];
        int[] buf = new int[n];
        return backtrack(n, vis, 0, buf);
    }

    private int backtrack(int n, boolean[] vis, int cur, int[] buf) {
        if (cur == n) {
            return 1;
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (vis[i]) {
                continue;
            }
            if (canXieAttack(buf, cur, i)) {
                continue;
            }
            buf[cur] = i;
            vis[i] = true;
            cnt += backtrack(n, vis, cur + 1, buf);
            vis[i] = false;
        }
        return cnt;
    }

    // 斜率为+1/-1，则斜向可攻击
    private boolean canXieAttack(int[] buf, int newRowNo, int newColNo) {
        for (int rowNo = 0; rowNo < newRowNo; rowNo++) {
            int colNo = buf[rowNo];
            if (Math.abs(rowNo - newRowNo) == Math.abs(colNo - newColNo)) {
                return true;
            }
        }
        return false;
    }

    /**
     * leetCode上最快算法
     * @param n
     * @return
     */
    public int totalNQueensBest(int n) {
        if (n < 1) {
            return 0;
        }

        // 1
        //int[] npos = new int[n];
        //return countQueens(0, npos);

        // 2
        long target = (1 << n) - 1;
        return countQueens2(0, 0, 0, target);
    }

    private int countQueens2(long current, long left, long right, long target) {
        if (current == target) {
            return 1;
        }

        int count = 0;
        long pos = ~(current | left | right) & target;
        while (pos != 0) {
            long p = pos & (-pos);
            pos ^= p;
            count += countQueens2(current | p, (left | p) << 1, (right | p) >> 1, target);
        }
        return count;
    }
}
