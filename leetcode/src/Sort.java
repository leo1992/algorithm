/**
 * Created by zhangying on 5/18/18.
 */
public class Sort {

    public void sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    public void quickSort(int[] nums, int start, int end) {
        if (start >= end || start < 0 || end >= nums.length) return;
        int base = nums[start];
        int leftIndex = start + 1;
        int rightIndex = end;
        while (leftIndex < rightIndex) {
            while (nums[leftIndex] <= base && leftIndex < rightIndex) leftIndex++;
            while (nums[rightIndex] >= base && rightIndex > leftIndex) rightIndex--;
            swap(nums, leftIndex, rightIndex);
        }
        if (nums[rightIndex] < base) {
            swap(nums, start, rightIndex);
        }
        quickSort(nums, start, rightIndex - 1);
        quickSort(nums, leftIndex, end);

    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
