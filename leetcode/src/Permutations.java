import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangying on 5/17/18.
 */
public class Permutations {
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        int length = nums.length;
        if (length == 0) return result;
        if (length == 1) {
            List<Integer> temp = new ArrayList<>();
            temp.add(nums[0]);
            result.add(temp);
            return result;
        }
        permuteRecursion(nums, 0);
        return result;
    }


    public void permuteRecursion(int[] nums, int curPos) {
        if (curPos > nums.length - 2) {
            return;
        }
        if (curPos == nums.length - 2) {
            addToList(nums);
            swap(nums, curPos, curPos + 1);
            addToList(nums);
            swap(nums, curPos, curPos + 1);
            return;
        }

        for (int i = curPos; i < nums.length; i++) {
            swap(nums, curPos, i);
            permuteRecursion(nums, curPos + 1);
            swap(nums, curPos, i);
        }
    }

    public void swap(int[] nums, int m, int n) {
        int temp = nums[m];
        nums[m] = nums[n];
        nums[n] = temp;
    }

    public void addToList(int[] nums) {
        int length = nums.length;
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < length; i++) temp.add(nums[i]);
        result.add(temp);
    }
}
