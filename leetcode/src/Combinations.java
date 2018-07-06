import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangying on 7/6/18.
 * https://leetcode.com/problems/combinations/description/
 */
public class Combinations {

    public List<List<Integer>> combine(int n, int k) {
        if (n == 0 || k == 0 || k > n) return null;
        List<List<Integer>> result = new ArrayList<>();
        if (k == n) {
            List<Integer> item = new ArrayList<>();
            for (int i = 1; i <= n; i++) item.add(i);
            result.add(item);
            return result;
        }
        if (k == 1) {
            for (int i = 1; i <= n; i++) {
                List<Integer> item = new ArrayList<>();
                item.add(i);
                result.add(item);
            }
            return result;
        }

        for (int i = 0; i < n; i++) {
            List<List<Integer>> temp = combine(n - i - 1, k - 1);
            if (temp != null) {
                for (List list: temp) {
                    list.add(n-i);
                    result.add(list);
                }
            }
        }
        return result;
    }

}
