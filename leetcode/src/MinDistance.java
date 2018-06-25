/**
 * Created by zhangying on 6/25/18.
 * https://leetcode.com/problems/edit-distance/description/
 */
public class MinDistance {

    public int minDistance(String word1, String word2) {
        int length1 = word1.length();
        int length2 = word2.length();
        if (length1 == 0) return length2;
        if (length2 == 0) return length1;
        char[] word1Char = word1.toCharArray();
        char[] word2Char = word2.toCharArray();

        int[][] distanceMap = new int[length1][length2];
        for (int i = length1 - 1; i >= 0; i--) {
            for (int j = length2 - 1; j >= 0; j--) {
                int isAdd = (word1Char[i] == word2Char[j]) ? 0 : 1;
                if (i == length1 - 1 && j == length2 - 1) {
                    distanceMap[i][j] = isAdd;
                } else if (i == length1 - 1) {
                    distanceMap[i][j] = Math.min(distanceMap[i][j + 1] + 1, length2 - j - 1 + isAdd);
                } else if (j == length2 - 1) {
                    distanceMap[i][j] = Math.min(distanceMap[i + 1][j] + 1, length1 - i - 1 + isAdd);
                } else {
                    distanceMap[i][j] = min(distanceMap[i + 1][j + 1] + isAdd,
                            distanceMap[i + 1][j] + 1,
                            distanceMap[i][j + 1] + 1);
                }
            }
        }
        return distanceMap[0][0];
    }

    private int min(int a, int b, int c) {
        int min = a;
        if (b < min) min = b;
        if (c < min) min = c;
        return min;
    }

}
