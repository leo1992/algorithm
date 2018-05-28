/**
 * Created by zhangying on 5/28/18.
 */
public class FindRange {

    public int[] searchRange(int[] nums, int target) {

        int length = nums.length;
        int[] result = {-1, -1};
        if (length == 0) return result;
        int start = 0;
        int end = length - 1;
        while (start < end) {
            int middle = (start + end) / 2;
            if (nums[middle] < target)
                start = middle + 1;
            if (nums[middle] > target)
                end = middle - 1;
            if (nums[middle] == target) {
                int resultS = middle;
                int resultE = middle;
                while ((resultS - 1) >= 0 && nums[resultS - 1] == target) resultS--;
                while ((resultE + 1) <= length - 1 && nums[resultE + 1] == target) resultE++;
                result[0] = resultS;
                result[1] = resultE;
                return result;
            }
        }
        return result;
    }

    /**
     * leetCode上的最佳算法
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRangeBest(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[] {-1, -1};
        }

        return new int[]{leftEdge(nums, target), rightEdge(nums, target)};
    }
    private int leftEdge(int[] nums, int target) {
        int left  = 0;
        int right = nums.length - 1;

        while (left + 1 < right) {//right指不到left的位置
            int mid = (right - left) / 2 + left;

            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;//只有在小于不等于target的时候才移动left
            }
        }
        //最终left一定是在target第一个值的左边，或者0
        if (nums[left] == target) {//第一个值==target，此时left == right == 0
            return left;
        } else if (nums[right] == target) {//否则 right已经移动到了第一个target的位置
            return right;
        } else {
            return -1;
        }
    }
    private int rightEdge(int[] nums, int target){
        int left  = 0;
        int right = nums.length - 1;

        while (left + 1 < right) {//left指不到right的位置
            int mid = (right - left) / 2 + left;

            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid;//只有在大于不等于target的时候才移动right
            }
        }
        //最终right一定是在target最边，或者length-1
        if (nums[right] == target) {//最后一个值==target，，此时left == right == length-1
            return right;
        } else if (nums[left] == target) {//left已经移动到了最后一个target的位置
            return left;
        } else {
            return -1;
        }
    }

}
