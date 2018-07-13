/**
 * Created by zhangying on 7/12/18.
 * https://leetcode.com/problems/largest-rectangle-in-histogram/description/
 */
public class LargestRectangleArea {

    public int largestRectangleArea(int[] heights) {
        int length = heights.length;
        if (length == 0) return 0;
        int result = heights[0];
        for (int i = 0; i < length; i++) {
            int min = heights[i];
            for (int j = i; j < length; j++) {
                if (heights[j] < min) min = heights[j];
                int temp = (j - i + 1) * min;
                if (temp > result) result = temp;
            }
        }
        return result;
    }

}
