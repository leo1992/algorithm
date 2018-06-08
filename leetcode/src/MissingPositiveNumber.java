/**
 * Created by zhangying on 6/8/18.
 * https://leetcode.com/problems/first-missing-positive/description/
 */
public class MissingPositiveNumber {

    public int firstMissingPositive(int[] nums) {
        int length = nums.length;
        int i = 0;
        while (i < length) {
            if (nums[i] > 0 && nums[i] <= length && nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            } else {
                i++;
            }
        }
        int j = 0;
        while (j < length && nums[j] == j + 1) j++;
        return j + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
