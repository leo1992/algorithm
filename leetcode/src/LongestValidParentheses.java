/**
 * Created by zhangying on 6/6/18.
 */
public class LongestValidParentheses {

    /**
     * 没在统计范围内，呵呵呵。。
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {

        int length = s.length();

        int maxLength = 0;
        for (int i = 0; i < length; i++) {
            int startIndex = -1;
            int endIndex = i;
            char curChar = s.charAt(i);
            int lastCaculate = (curChar == '(') ? 1 : -1;
            for (int j = i + 1; j < length; j++) {
                if (lastCaculate == -1) break;
                if (lastCaculate == 1 && startIndex == -1) startIndex = j - 1;
                curChar = s.charAt(j);
                int add = (curChar == '(') ? 1 : -1;
                lastCaculate += add;
                if (lastCaculate == 0) endIndex = j;
            }
            if (startIndex != -1 && endIndex - startIndex > maxLength) maxLength = endIndex - startIndex + 1;
        }
        return maxLength;
    }

    /**
     * 优化一下，正反各算一次，79.65%
     * @param s
     * @return
     */
    public int longestValidParenthesesOpt(String s) {
        int length = s.length();
        int curCal = 0;
        int startIndex = 0;
        int maxLength = 0;
        for (int i = 0; i < length; i++) {
            boolean isPos = s.charAt(i) == '(';
            if (isPos) curCal++;
            else curCal--;
            if (curCal == 0 && startIndex != -1 && (i - startIndex + 1 > maxLength)) {
                maxLength = i - startIndex + 1;
            } else if (curCal == -1) {
                curCal = 0;
                startIndex = -1;
            } else if (curCal == 1 && startIndex == -1) {
                startIndex = i;
            }
        }

        if (curCal <= 0) return maxLength;
        startIndex = -1;
        curCal = 0;
        for (int i = length - 1; i >= 0; i--) {
            boolean isPos = s.charAt(i) == ')';
            if (isPos) curCal++;
            else curCal--;
            if (curCal == 0 && startIndex != -1 && (startIndex - i + 1> maxLength)) {
                maxLength = startIndex - i + 1;
            } else if (curCal == -1) {
                curCal = 0;
                startIndex = -1;
            } else if (curCal == 1 && startIndex == -1) {
                startIndex = i;
            }
        }

        return maxLength;
    }

    /**
     * leetcode上最快速的解法
     * @param s
     * @return
     */
    public int longestValidParenthesesBest(String s) {
        int max = 0, n = s.length();
        int[] dp = new int[n];
        for(int i = 1; i < n; i++) {
            if(s.charAt(i) == ')') {
                if(s.charAt(i - 1) == '(') {
                    dp[i] = 2 + (i >= 2 ? dp[i-2] : 0);
                }
                // s.charAt(i - 1) == )
                else if(i - dp[i-1] > 0 && s.charAt(i-dp[i-1]-1) == '(') {
                    dp[i] = 2 + dp[i-1] + ((i - dp[i-1]) >= 2 ? dp[i-dp[i-1]-2] : 0);
                }

                max = Math.max(max, dp[i]);
            }
        }

        return max;
    }
}
