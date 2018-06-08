/**
 * Created by zhangying on 6/8/18.
 * https://leetcode.com/problems/trapping-rain-water/description/
 */
public class RainWater {

    public int trap(int[] height) {
        int length = height.length;
        if (length == 0) return 0;
        int lastHighest = height[0];
        int lastHighestIndex = 0;
        int middleCount = 0;
        int result = 0;

        for (int i = 1; i < length; i++) {
            if (height[i] >= lastHighest) {
                result += (i - lastHighestIndex - 1) * lastHighest - middleCount;
                lastHighest = height[i];
                lastHighestIndex = i;
                middleCount = 0;
            } else {
                middleCount += height[i];
            }
        }
        result += trapReverse(height, lastHighestIndex, length);
        return result;
    }

    public int trapReverse(int[] height, int endIndex, int length) {
        int lastHighest = height[length - 1];
        int lastHighestIndex = length - 1;
        int middleCount = 0;
        int result = 0;

        for (int i = length - 2; i >= endIndex; i--) {
            if (height[i] >= lastHighest) {
                result += (lastHighestIndex - i - 1) * lastHighest - middleCount;
                lastHighest = height[i];
                lastHighestIndex = i;
                middleCount = 0;
            } else {
                middleCount += height[i];
            }
        }
        return result;
    }
}
