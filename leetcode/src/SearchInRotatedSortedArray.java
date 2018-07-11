/**
 * Created by zhangying on 7/11/18.
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii
 */
public class SearchInRotatedSortedArray {

    public boolean search(int[] nums, int target) {
        return search(nums, 0, nums.length - 1, target);
    }

    public boolean search(int[] nums, int start, int end, int target) {
        if (start > end) return false;
        int middle = start + (end - start) / 2;
        if (nums[middle] == target) return true;

        if (nums[middle] < nums[start]) {
            if (nums[middle] < target) {
                return search(nums, middle + 1, end, target) || search(nums, start, middle - 1, target);
            } else {
                return search(nums, start, middle -1, target);
            }
        } else if (nums[middle] > nums[start]) {
            if (nums[middle] < target) {
                return search(nums, middle + 1, end, target);
            } else {
                return search(nums, middle + 1, end, target) || search(nums, start, middle - 1, target);
            }
        } else if (nums[middle] == nums[start] && nums[middle] < nums[end]) {
            if (nums[middle] < target)
                return search(nums, middle + 1, end, target);
            return false;
        } else if (nums[middle] == nums[start] && nums[middle] > nums[end]) {
            if (nums[middle] > target)
                return search(nums, middle + 1, end, target);
            return false;
        } else {
            return search(nums, middle + 1, end, target) || search(nums, start, middle - 1, target);
        }
    }

//    public boolean search(int[] nums, int start, int end, int target) {
//        if (start > end) return false;
//        int middle = start + (end - start) / 2;
//        if (nums[middle] == target) return true;
//        //左边是完全递增的，并且target比midde上的值还大，肯定在右边
//        if (nums[middle] > nums[start] && target > nums[middle]) {
//            return search(nums, middle+1, end, target);
//        }
//        //左边完全递增，target比start上的值小，也比end可能在右边
//        else if (nums[middle] > nums[start] && target < nums[start]) {
//            return search(nums, middle+1, end, target);
//        }
//        else if (nums[middle] > nums[start] && target)
//        //左边是递增的
//        if (nums[middle] < nums[start]) {
//            if (nums[middle] < target) {
//                return search(nums, middle + 1, end, target) || search(nums, start, middle - 1, target);
//            } else {
//                return search(nums, start, middle, start);
//            }
//        } else if (nums[middle] > nums[start]) {
//            if (nums[middle] < target) {
//                return search(nums, middle + 1, end, target);
//            } else {
//                return search(nums, middle + 1, end, target) || search(nums, start, middle - 1, target);
//            }
//        }
//        return false;
//    }
}
