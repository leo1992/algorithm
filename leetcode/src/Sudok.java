/**
 * Created by zhangying on 6/7/18.
 */
public class Sudok {

    private boolean row[][] = new boolean[9][9];
    private boolean colume[][] = new boolean[9][9];
    private boolean square[][] = new boolean[9][9];

    /**
     * 加了前期优化的处理，从14ms 到 9 ms
     * @param board
     */
    public void solveSudoku(char[][] board) {
        initMap(board);
        initCandidata(board);
        sudoku(board);
    }

    private boolean sudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') continue;
                for (char c = '1'; c <= '9'; c++) {
                    if (isValid(board, i, j, c)) {
                        board[i][j] = c;
                        if (sudoku(board)) return true;
                        else board[i][j] = '.';
                    }
                }
                return false;
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int column, char c) {
        int squareRowStartIndex = (row / 3) * 3;
        int squareColumnStartIndex = (column / 3) * 3;
        for (int i = 0; i < 9; i++) {
            if (c == board[i][column]) return false;
            if (c == board[row][i]) return false;
            if (c == board[squareRowStartIndex + i / 3][squareColumnStartIndex + i % 3]) return false;
        }
        return true;
    }

    private int initMap(char[][] board) {
        int count = 0;
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int number = board[i][j] - '1';
                    board[i][j] = (char) ('1' + number);
                    onSetValue(i, j, number);
                } else {
                    count++;
                }
            }
        return count;
    }

    private void initCandidata(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') continue;
                int count = 0;
                int lastValid = -1;
                for (int k = 0; k < 9; k++) {
                    if (!getSquare(i, j, k) && !row[i][k] && !colume[j][k]) {
                        count++;
                        lastValid = k;
                    }
                }
                if (count == 1) {
                    board[i][j] = (char) ('1' + lastValid);
                    onSetValue(i, j, lastValid);
                }
            }
        }
    }

    private void onSetValue(int i, int j, int number) {
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
