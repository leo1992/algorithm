import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangying on 5/18/18.
 */
public class PermutationsAdvanced {
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        int length = nums.length;
        if (length == 0) return result;
        if (length == 1) {
            List<Integer> temp = new ArrayList<>();
            temp.add(nums[0]);
            result.add(temp);
            return result;
        }
        new Sort().sort(nums);
        permuteRecursion(nums, 0, new boolean[length]);
        return result;
    }

    public void permuteRecursion(int[] nums, int curPos, boolean[] used) {
        if (curPos >= nums.length) return;
        if (curPos == nums.length - 1) {
            addToList(nums);
            return;
        }

        for (int i = curPos; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            swap(nums, curPos, i);
            permuteRecursion(nums, curPos + 1, used);
            used[i] = false;
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
