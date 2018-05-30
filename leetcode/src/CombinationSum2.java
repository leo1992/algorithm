import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhangying on 5/30/18.
 */
public class CombinationSum2 {

    //已经基于CombinationSum的最优提交去想，还是有3%的更优解法，真是前方望不到尽头啊~
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum(candidates, 0, new Integer[target], new Integer[target]/*改为int的时候竟然耗时更长*/, 0, result, target);
        return result;
    }

    private void combinationSum(int[] candidates, int start,
                                Integer[] map, Integer[] mapSum, int len,
                                List<List<Integer>> result, int target) {
        if (target == 0) {
            Integer[] temp = new Integer[len];
            System.arraycopy(map, 0, temp, 0, len);
            result.add(Arrays.asList(temp));
            return;
        }
        int length = candidates.length;
        for (int i = start; i < length; i++) {
            if (candidates[len] > target) break;
            /*
            三次调整，当前要存放的位置值跟上次的一样，并且之前的累加和一样（用target去判断，因为target = firstTarget - 累加和）
             */
            if (map[len] != null && map[len].intValue() == candidates[i] && target == mapSum[len]) continue;
            map[len] = candidates[i];
            mapSum[len] = target;
            combinationSum(candidates, i + 1, map, mapSum, len + 1, result, target - candidates[i]);
        }

    }

    public List<List<Integer>> combinationSum2Best(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        search(candidates, 0, path, 0, target, ret);
        return ret;
    }

    void search(int[] arr, int s, List<Integer> path, int sum, int target, List<List<Integer>> ret) {
        if (sum == target) {
            ret.add(new ArrayList<>(path));
            return;
        }

        for (int i = s; i < arr.length && arr[i] + sum <= target; i++) {
            if (i > s && arr[i] == arr[i - 1]) {
                continue;
            }

            path.add(arr[i]);
            search(arr, i + 1, path, sum + arr[i], target, ret);
            path.remove(path.size() - 1);
        }
    }
}
