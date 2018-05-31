/**
 * Created by zhangying on 5/31/18.
 */
public class Rotate {

    /**
     * 第一次麻烦了，一个一个占位交换足够，运算次数为
     * 奇数： (2 + 4 +... n-1) * 4 = (2+n-1)(n-1)/4)* 4= n^2-1
     * 偶数： （1 + 3 + ... n-1） * 4 = ((1 + n-1)n/4) * 4 = n^2
     * 没有多余运算
     *
     * @param matrix
     */
    public void rotateOpt(int[][] matrix) {
        int a = matrix.length;
        for (int i = 0; i < a / 2; i++) {
            for (int j = i; j < a - i - 1; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[a - 1 - j][i];
                matrix[a - 1 - j][i] = matrix[a - 1 - i][a - 1 - j];
                matrix[a - 1 - i][a - 1 - j] = matrix[j][a - 1 - i];
                matrix[j][a - 1 - i] = temp;
            }
        }
    }

    public void rotate(int[][] matrix) {
        int a = matrix.length;
        int cache[] = new int[a];
        for (int i = 0; i < a / 2; i++) {

            PrintUtils printUtils = new PrintUtils();
            rotateFirstRow(i, a, matrix, cache);
//            printUtils.print(matrix);
//            printUtils.print(cache);

            rotateLastColumn(i, a, matrix, cache);
//            printUtils.print(matrix);
//            printUtils.print(cache);

            rotateLastRow(i, a, matrix, cache);
//            printUtils.print(matrix);
//            printUtils.print(cache);

            rotateFirstColumn(i, a, matrix, cache);
//            printUtils.print(matrix);
//            printUtils.print(cache);

        }
    }

    private void rotateFirstRow(int i, int a, int[][] data, int[] cache) {
        for (int x = a - 1 - i; x >= i; x--) {
            cache[x] = data[i][x];
            data[i][x] = data[a - 1 - x][i];
        }
    }

    private void rotateLastColumn(int i, int a, int[][] data, int[] cache) {
        for (int x = i + 1; x < a - i; x++) {
            int temp = cache[x];
            cache[x] = data[x][a - 1 - i];
            data[x][a - 1 - i] = temp;
        }
    }

    private void rotateLastRow(int i, int a, int[][] data, int[] cache) {
        for (int x = i; x < a - i - 1; x++) {
            int temp = cache[a - 1 - x];
            cache[a - 1 - x] = data[a - 1 - i][x];
            data[a - 1 - i][x] = temp;
        }

    }

    private void rotateFirstColumn(int i, int a, int[][] data, int[] cache) {
        for (int x = i + 1; x < a - 1 - i; x++) {
            data[x][i] = cache[a - 1 - x];
        }
    }

    /**
     * leetcode 上的解答，发现了开始有多余的判断,但是修改后结果竟然运算时间变长了。。。。
     *
     * @param matrix
     */
    public void rotateCommon(int[][] matrix) {
        int len = matrix.length - 1, level = 0, current = 0;
        //奇数最后一次是1个数据，不用旋转
        if ((len & 1) == 1) {
            level = (len >> 1) + 1; //奇数 运算  len/2 + 1 行
        } else {
            level = len >> 1;// 偶数 运算 len/2行
        }
        while (current <= level) {
            for (int i = current; i <= len - current - 1; i++) {
                int tmp1 = matrix[i][len - current];
                matrix[i][len - current] = matrix[current][i];
                int tmp2 = matrix[len - current][len - i];
                matrix[len - current][len - i] = tmp1;
                tmp1 = matrix[len - i][current];
                matrix[len - i][current] = tmp2;
                matrix[current][i] = tmp1;
            }
            current++;
        }
    }

}
