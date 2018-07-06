import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangying on 7/6/18.
 * https://leetcode.com/problems/subsets/description/
 */
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        if (n == 0) return result;
        for (int i = n; i > 0; i--) {
            result.addAll(combine(nums, n, i));
        }
        return result;
    }

    private List<List<Integer>> combine(int[] nums, int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (k == 1) {
            for (int i = 0; i < n; i++) {
                List<Integer> item = new ArrayList<>();
                item.add(nums[i]);
                result.add(item);
            }
            return result;
        }
        if (n == k) {
            List<Integer> item = new ArrayList<>();
            for (int i = 0; i < n; i++) item.add(nums[i]);
            result.add(item);
            return result;
        }
        for (int i = 1; i < n ; i++) {
            List<List<Integer>> temp = combine(nums, n - i, k - 1);
            if (temp != null) {
                for (List list : temp) {
                    list.add(nums[n - i]);
                    result.add(list);
                }
            }
        }
        return result;
    }

}
