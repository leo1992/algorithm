/**
 * Created by zhangying on 7/6/18.
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/
 * 一次通过，并且时间打败1000%，(*^▽^*)
 */
public class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        if (length < 3) return nums.length;
        int index = 2;
        for (int i = 2; i < length; i++) {
            if (nums[i] == nums[index - 1] && nums[i] == nums[index - 2]) {

            } else {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }

}
