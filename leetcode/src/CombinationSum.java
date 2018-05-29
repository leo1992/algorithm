import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhangying on 5/29/18.
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        return findList(candidates, candidates.length - 1, target);
    }

    public List<List<Integer>> findList(int[] candidates, int end, int target) {
        int length = candidates.length;
        if (end >= length || end < 0 || target <= 0) return null;
        int i = end;
        List<List<Integer>> result = new ArrayList<>();
        while (i >= 0 && candidates[i] > target) i--;
        for (; i >= 0; i--) {
            int multi = target / candidates[i];
            for (int j = 0; j < multi; j++) {
                List<Integer> list = new ArrayList<>();
                for (int m = 0; m <= j; m++) list.add(candidates[i]);
                int subTarget = target - ((j + 1) * candidates[i]);
                if (subTarget == 0 && list.size() > 0) {
                    result.add(list);
                    continue;
                }
                if (subTarget < 0) continue;
                List<List<Integer>> subList = findList(candidates, i - 1, subTarget);
                if (subList == null || subList.size() <= 0) continue;
                for (int k = 0; k < subList.size(); k++) {
                    List<Integer> temp = new ArrayList<>();
                    temp.addAll(list);
                    temp.addAll(subList.get(k));
                    result.add(temp);
                }
            }
        }
        return result;
    }

    /**
     * Leetcode上最佳提交
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSumBest(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        // 用数组代理list轻便快捷
        search(candidates, 0, target, new Integer[target], 0, ans);
        return ans;
    }

    private void search(int[] candidates, int st,
                        int target,
                        Integer[] paper, int len,
                        List<List<Integer>> ans) {
        if (target == 0) {
            Integer[] temp = new Integer[len];
            System.arraycopy(paper, 0, temp, 0, len);
            ans.add(Arrays.asList(temp));
            return;
        }

        for (int i = st; i < candidates.length; i++) {
            if (target < candidates[i]) break;
            paper[len] = candidates[i];
            search(candidates, i/*依然从i开始，可重复*/, target - candidates[i], paper, len + 1/*存储到下一个位置*/, ans);
        }
    }

}
