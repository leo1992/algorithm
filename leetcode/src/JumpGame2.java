/**
 * Created by zhangying on 6/8/18.
 * https://leetcode.com/problems/jump-game-ii/description/
 */
public class JumpGame2 {

    /**
     * 超时。。。
     * @param nums
     * @return
     */
    public int jumpDrop(int[] nums) {
        int length = nums.length;
        if (length <= 1) return 0;
        int[] map = new int[length];

        map[length - 1] = 0;
        for (int i = 0; i < length; i++) {
            if (i + nums[i] >= length - 1) map[i] = 1;
        }
        if (map[0] == 1) return 1;

        for (int i = length - 2; i >= 0; i--) {
            if (map[i] == 1) continue;
            int count = nums[i];
            map[i] = Integer.MAX_VALUE;
            for (int j = i + 1; j <= i + count; j++) {
                if (j >= length - 1 || map[j] == 0) {
                    map[i] = 1;
                    break;
                } else if (map[j] < map[i]) {
                    map[i] = map[j] + 1;
                }
            }
        }
        return map[0];
    }

    /**
     * 贪心算法
     * @param nums
     * @return
     */
    public int jump(int[] nums) {

        int length = nums.length;
        if (length <= 1) return 0;
        int jump = 0;
        int end = nums[0];

        int start = 0;
        while (end < length) {
            jump++;
            int farthest = end;
            for (int i = start; i <= end; i++) {
                if (i + nums[i] > farthest) {
                    farthest = i + nums[i];
                }
            }
            start = end + 1;
            end = farthest;

        }
        return jump;
    }
}
