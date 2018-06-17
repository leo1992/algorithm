/**
 * Created by zhangying on 6/17/18.
 * https://leetcode.com/problems/jump-game/description/
 */
public class JumpGame {
    public boolean canJump(int[] nums) {
        int length = nums.length;
        if (length <= 1) return true;
        boolean[] jumps = new boolean[length];
        jumps[length - 1] = true;
        for (int i = length - 2; i >= 0; i--) {
            int step = nums[i];
            for (int j = 1; j <= step && i + j < length; j++) {
                if (jumps[i + j]) {
                    jumps[i] = true;
                    break;
                }
            }
        }
        return jumps[0];
    }

    /**
     * leetcode 上最快
     * @param nums
     * @return
     */
    public boolean canJumpBest(int[] nums) {
        int leftMostGoodPosition = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            // 如果当前位置能走的比leftMostGoodPosition要大，
            // 说明能够走到leftMostGoodPosition的位置，能走到就意味着下一个只要能达到这个位置
            // 就能走到终点
            if (i + nums[i] >= leftMostGoodPosition) {
                leftMostGoodPosition = i;
            }
        }

        // 0的位置没有记录，也就是走不到终点
        return leftMostGoodPosition == 0;
    }
}
