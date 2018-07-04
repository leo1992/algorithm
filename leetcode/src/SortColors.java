/**
 * Created by zhangying on 6/26/18.
 * https://leetcode.com/problems/sort-colors
 */
public class SortColors {

    public void sortColors(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        int k = j;
        while (i < j) {
            while (i < j && nums[i] < 2) {
                if (nums[i] == 1 && k > i) k = i;
                i++;
            }
            while (j > i && nums[j] == 2) j--;
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            if (nums[i] == 1 && k > i) k = i;
            i++;
            if (nums[j] == 2) j--;
        }
        while (k < j) {
            while (k < j && nums[k] == 0) k ++;
            while (j> k && nums[j] == 1) j--;
            int temp = nums[k];
            nums[k] = nums[j];
            nums[j] = temp;
            k++;
            j--;
        }
    }

}
