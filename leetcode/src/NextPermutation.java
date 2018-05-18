/**
 * Created by zhangying on 5/17/18.
 */
public class NextPermutation {

    public void run() {
        int[] nums = {1, 4, 2, 3, 5};
        nextPermutation(nums);
    }

    public void run(int[] nums) {
        nextPermutation(nums);
    }

    public void nextPermutation(int[] nums) {
        int length = nums.length;
        if (length < 2) return;
        int start = nums.length - 1;
        while (start > 0 && nums[start - 1] >= nums[start]) start--;
        for (int i = start; i <= start + (length - start - 1) / 2; i++) {
            int temp = nums[i];
            nums[i] = nums[length - 1 - i + start];
            nums[length - 1 - i + start] = temp;
        }
        int i = start;
        while (start > 0 && i < length && nums[i] <= nums[start - 1]) i++;
        if (i >= length) i = length - 1;
        if (start > 0) {
            int temp = nums[start - 1];
            nums[start - 1] = nums[i];
            nums[i] = temp;
        }

    }
}
