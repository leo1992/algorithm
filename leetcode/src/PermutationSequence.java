/**
 * Created by zhangying on 6/20/18.
 * https://leetcode.com/problems/permutation-sequence/description/
 */
public class PermutationSequence {

    /**
     * 一次过and 超过99% O(∩_∩)O~~
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        int[] map = new int[n];
        char[] result = new char[n];

        for (int i = 0; i < n; i++) {
            if (i == 0) map[n - i - 1] = 1;
            else map[n - i - 1] = map[n - i ] * (i + 1);
            result[i] = (char) ('0' + i + 1);
        }
        int[] kMap = new int[n];
        int tempK = k;
        for (int j = 0; j < n - 1 && tempK > 0; j++) {
            if (map[j + 1] <= tempK) {
                kMap[j] = (tempK - 1) / map[j + 1];
                tempK -= kMap[j] * map[j + 1];
            }
        }

        for (int j = 0; j < n; j++) {
            if (kMap[j] != 0) {
                recursion(result, j, kMap[j]);
            }
        }
        return new String(result);
    }

    private void recursion(char[] data, int index, int multi) {
        char temp = data[index + multi];
        for (int i = index + multi; i > index; i--) data[i] = data[i - 1];
        data[index] = temp;
    }
}
