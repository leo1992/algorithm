/**
 * Created by zhangying on 6/21/18.
 * https://leetcode.com/problems/valid-number/description/
 */
public class IsNumber {

    private static final char E = 'e';

    public boolean isNumber(String s) {
        s = s.trim();

        int length = s.length();
        if (length == 0) return false;

        boolean countE = false;
        for (char c : s.toCharArray()) {
            if (c == 'e') {
                if (countE) return false;
                countE = true;
            }
        }

        if (s.charAt(0) == E || s.charAt(length - 1) == E) return false;
        String[] find = s.split("e");
        if (find.length > 2) return false;

        int size = find.length;
        for (int i = 0 ; i < size; i++) {
            if (!isValidExcludeE(find[i], i == 0)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidExcludeE(String s, boolean canHasPoint) {
        boolean countPoint = false;
        for (char c : s.toCharArray()) {
            if (c == '.') {
                if (countPoint || !canHasPoint) return false;
                countPoint = true;
            }
        }
        if (s.startsWith("-") || s.startsWith("+")) s = s.substring(1);
        if (s.length() == 0) return false;
        String[] point = s.split("\\.");
        if (point.length == 0) return false;
        if (point.length > 2) return false;
        for (String item : point) {
            if (!isAllNumber(item)) return false;
        }
        return true;
    }

    private boolean isAllNumber(String s) {
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char temp = s.charAt(i);
            if (temp < '0' || temp > '9') return false;
        }
        return true;
    }

}
