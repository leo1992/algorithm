import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhangying on 6/7/18.
 */
public class SudokDrop {

    private boolean row[][] = new boolean[9][9];
    private boolean colume[][] = new boolean[9][9];
    private boolean square[][] = new boolean[9][9];
    private HashMap<Integer, List> map;
    private char[][] board;
    private int numberCount;

    /**
     * 第一次按照解数独的优化思路，走死了，废弃，结合进常规的解法，速度有提升
     * @param board
     */
    public void solveSudoku(char[][] board) {
        this.board = board;
        map = new HashMap<>();
        numberCount = initMap();
        int lastNumber = 0;
        initCandidata();
        while (lastNumber != numberCount) {
            lastNumber = numberCount;
            caculateCandidate();
        }
    }

    private int initMap() {
        int count = 0;
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int number = board[i][j] - '1';
                    setValue(i, j, number);
                } else {
                    count++;
                }
            }
        return count;
    }

    private void initCandidata() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int index = i * 10 + j;
                List<Integer> list = map.get(index);
                if (board[i][j] != '.') {
                    map.remove(index);
                    continue;
                }
                if (list == null) {
                    list = new ArrayList();
                    map.put(index, list);
                }
                for (int k = 0; k < 9; k++) {
                    if (!getSquare(i, j, k) && !row[i][k] && !colume[j][k]) {
                        list.add(Integer.valueOf(k));
                    }
                }
                if (list.size() == 1) {
                    setValue(i, j, list.get(0));
                    map.remove(index);
                    numberCount++;
                } else if (list.size() == 0) {
                    return;
                }
            }
        }
    }

    private boolean caculateCandidate() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int index = i * 10 + j;
                List<Integer> list = map.get(index);
                if (board[i][j] != '.') {
                    map.remove(index);
                    continue;
                }
                if (list == null) {
                    return false;
                }
                for (int m = 0; m < list.size(); ) {
                    int number = list.get(m);
                    if (getSquare(i, j, number) || row[i][number] || colume[j][number]) {
                        list.remove(m);
                        continue;
                    }
                    m++;
                }
                if (list.size() == 1) {
                    setValue(i, j, list.get(0));
                    map.remove(index);
                    numberCount++;
                } else if (list.size() == 0) return false;
            }
        }
        return true;
    }

    private void setValue(int i, int j, int number) {
        board[i][j] = (char) ('1' + number);
        row[i][number] = true;
        colume[j][number] = true;
        setSquare(i, j, number, true);
    }

    private void setSquare(int i, int j, int number, boolean isAvaliable) {
        int index = 3 * (i / 3) + j / 3;
        square[index][number] = isAvaliable;
    }

    private boolean getSquare(int i, int j, int number) {
        int index = 3 * (i / 3) + j / 3;
        return square[index][number];
    }

}
