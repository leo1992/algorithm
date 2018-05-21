import java.util.Arrays;

/**
 * Created by zhangying on 5/21/18.
 */
public class Divide {

    private static final int POSPOS = 1;
    private static final int NEGPOS = 2;
    private static final int NEGNEG = 3;
    private static final int POSNEG = 4;

    public int divide(int dividend, int divisor) {
        if (divisor == 0 || dividend == 0) return 0;
        if (divisor == 1) return dividend;

        long sum = 0;
        long result = 0;
        int state = state(dividend, divisor);
        int[] record = new int[10];
        record[0] = divisor;

        int i = 0;
        for (; i < Integer.MAX_VALUE / 2; i++) {
            if (i > 0) {
                record[i] = record[i - 1] + divisor;
                if (divisor > 0 && record[i] < record[i - 1]) break;
                if (divisor < 0 && record[i] > record[i - 1]) break;
            }

            if (hasFoundRange(dividend, sum + record[i], state)) break;
            sum = sum + record[i];
            result = result + i + 1;
            if (i == record.length - 1) {
                record = Arrays.copyOf(record, record.length + 10);
            }
        }
        for (; i >= 0; i--) {
            if (hasFoundExactResult(dividend, sum + record[i], state))
                return returnResult(state, result + i + 1);
            if (hasFoundResult(dividend, divisor, sum + record[i], state)) {
                return returnResult(state, result + i);
            }
        }
        return (int) result;
    }

    private int state(int dividend, int divisor) {
        if (dividend > 0 && divisor > 0) {
            return POSPOS;
        } else if (dividend < 0 && divisor > 0) {
            return NEGPOS;
        } else if (dividend < 0 && divisor < 0) {
            return NEGNEG;
        } else if (dividend > 0 && divisor < 0) {
            return POSNEG;
        }
        return POSPOS;
    }

    private boolean hasFoundRange(int dividend, long curSum, int state) {
        if (state == POSPOS) {
            if (curSum >= dividend) return true;
        } else if (state == NEGPOS) {
            if (dividend + curSum >= 0) return true;
        } else if (state == NEGNEG) {
            if (dividend >= curSum) return true;
        } else if (state == POSNEG) {
            if (dividend + curSum <= 0) return true;
        }
        return false;
    }

    private boolean hasFoundExactResult(int dividend, long curSum, int state) {
        switch (state) {
            case POSPOS:
            case NEGNEG:
                return (curSum == dividend);
            case NEGPOS:
            case POSNEG:
                return (dividend + curSum == 0);
        }
        return false;
    }

    private boolean hasFoundResult(int dividend, int divisor, long curSum, int state) {
        if (state == POSPOS) {
            if (curSum == dividend) return true;
            if (dividend < curSum && (curSum - divisor) < dividend) return true;
        } else if (state == NEGPOS) {
            if (dividend + curSum == 0) return true;
            if (dividend + curSum > 0 && (dividend + curSum - divisor) < 0) return true;
        } else if (state == NEGNEG) {
            if (dividend == curSum) return true;
            if (dividend > curSum && (curSum - divisor) > dividend) return true;
        } else if (state == POSNEG) {
            if (dividend + curSum == 0) return true;
            if (dividend + curSum < 0 && dividend + curSum - divisor > 0) return true;
        }
        return false;
    }

    private int returnResult(int state, long result) {
        switch (state) {
            case POSPOS:
            case NEGNEG:
                if (result > 0x7fffffff || result < 0x80000000) return 0x7fffffff;
                return (int) result;
            case NEGPOS:
            case POSNEG:
                if ((-result) > 0x7fffffff || (-result) < 0x80000000) return 0x7fffffff;

                return (int) -result;
        }
        return -1;
    }

    //leetcode上的最佳做法

    /**
     * 转换为long类型
     * 原理：
     * dividend = [a0*(2^k)+a1*(2^(k-1))+ ... + ak*(2^0)] divisor + dividend % divisor  ai = 0/1 (i=0...k);
     * result = [a0*(2^k)+a1*(2^(k-1))+ ... + ak*(2^0)]
     * a0 = dividend / (2^k) => 循环(divisor << 1) 直到  divisor 在下一次左移运算中 > dividend
     * a1 = (dividend - a0*(2^k)*divisor) / (2^k-1) 循环(divisor << 1) 直到  divisor 在下一次左移运算中 > dividend
     * ...
     */
    public int divideBest(int dividend, int divisor) {
        if (divisor == 0 || dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        int res = 0;
        int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;
        long dvd = Math.abs((long) dividend);
        long dvs = Math.abs((long) divisor);
        while (dvs <= dvd) {
            long temp = dvs, mul = 1;
            while (dvd >= temp << 1) {
                temp <<= 1;
                mul <<= 1;
            }
            dvd -= temp;
            res += mul;
        }
        return sign == 1 ? res : -res;
    }

    // leetcode上的常规做法
    public int divideCommon(int dividend, int divisor) {


        int sign = 1;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
            sign = -1;
        }


        long ldividend = Math.abs((long) dividend);
        long ldivisor = Math.abs((long) divisor);
        if (ldividend < ldivisor || dividend == 0)
            return 0;
        long res = div(ldividend, ldivisor);
        if (res > Integer.MAX_VALUE) {
            return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        return (int) res * sign;
    }

    private long div(long ldividend, long ldivisor) {
        if (ldividend < ldivisor) return 0;

        long sum = ldivisor, multiple = 1;

        while (sum + sum <= ldividend) {
            sum += sum;
            multiple += multiple;
        }

        return multiple + div(ldividend - sum, ldivisor);


    }
}
