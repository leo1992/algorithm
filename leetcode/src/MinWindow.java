import java.util.HashMap;

/**
 * Created by zhangying on 6/29/18.
 * https://leetcode.com/problemset/all/
 */
public class MinWindow {

    public String minWindow(String s, String t) {

        int tLength = t.length();
        int sLength = s.length();

        if (tLength == 0 || sLength == 0 || tLength > sLength) return "";

        HashMap<Character, Integer> target = new HashMap<>();
        HashMap<Character, Integer> source = new HashMap<>();

        for (int i = 0; i < tLength; i++) {
            char c = t.charAt(i);
            target.put(c, target.get(c) == null ? 1 : target.get(c) + 1);
        }

        int start = 0;
        int end = 0;
        while (end < sLength && !isContainTarget(source, target)) {
            char endC = s.charAt(end);
            if (target.get(endC) != null) {
                source.put(endC, source.get(endC) == null ? 1 : source.get(endC) + 1);
            }
            end++;
        }
        if (!isContainTarget(source, target)) return "";

        char startC = s.charAt(start);
        while (start < end &&
                (target.get(startC) == null ||
                        (source.get(startC) - 1) >= target.get(startC))) {
            if (source.get(startC) != null) source.put(startC, source.get(startC) - 1);
            start++;
            startC = s.charAt(start);
        }

        String result = s.substring(start, end);
        int window = end;
        for (; start < end && end < sLength; end++) {
            char endC = s.charAt(end);
            if (source.get(endC) != null) {
                source.put(endC, source.get(endC) + 1);
            }
            startC = s.charAt(start);
            while (start < end &&
                    (target.get(startC) == null ||
                            (source.get(startC) - 1) >= target.get(startC))) {
                if (source.get(startC) != null) source.put(startC, source.get(startC) - 1);
                start++;
                startC = s.charAt(start);
            }
            if (end - start + 1 < window) {
                window = end - start + 1;
                result = s.substring(start, end + 1);
            }
        }
        if (result.length() <= sLength) return result;
        return "";
    }

    private boolean isContainTarget(HashMap<Character, Integer> source, HashMap<Character, Integer> target) {
        if (source == null || target == null) return false;
        if (source.size() != target.size()) return false;
        for (char c : target.keySet()) {
            if (source.get(c) == null) return false;
            if (!(source.get(c) >= target.get(c))) return false;
        }
        return true;
    }

    public String minWindowArray(String s, String t) {

        int tLength = t.length();
        int sLength = s.length();

        if (tLength == 0 || sLength == 0 || tLength > sLength) return "";

        int[] target = new int[128];
        int[] source = new int[128];

        for (int i = 0; i < tLength; i++) {
            char c = t.charAt(i);
            target[c]++;
        }

        int start = 0;
        int end = 0;
        while (end < sLength && !isContainTarget(source, target)) {
            char endC = s.charAt(end);
            source[endC]++;
            end++;
        }
        if (!isContainTarget(source, target)) return "";

        char startC = s.charAt(start);
        while (start < end && source[startC] - 1 >= target[startC]) {
            source[startC]--;
            start++;
            startC = s.charAt(start);
        }

        String result = s.substring(start, end);
        int window = end - start;
        for (; start < end && end < sLength; end++) {
            char endC = s.charAt(end);
            source[endC]++;
            startC = s.charAt(start);
            while (start < end && source[startC] - 1 >= target[startC]) {
                source[startC]--;
                start++;
                startC = s.charAt(start);
            }
            if (end - start + 1 < window) {
                window = end - start + 1;
                result = s.substring(start, end + 1);
            }
        }
        if (result.length() <= sLength) return result;
        return "";
    }

    private boolean isContainTarget(int[] source, int[] target) {
        for (int i = 0; i < 128; i++) {
            if (source[i] < target[i]) return false;
        }
        return true;
    }

}
