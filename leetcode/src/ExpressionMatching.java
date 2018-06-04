/**
 * Created by zhangying on 6/1/18.
 */
public class ExpressionMatching {

    public boolean isMatch(String s, String p) {
        p = trimMulti(p);
        int lengthP = p.length();
        int lengthS = s.length();
        int i = lengthS - 1;
        int j = lengthP - 1;
        for (; i >= 0 && j >= 0; ) {
            if (p.charAt(j) == s.charAt(i) || p.charAt(j) == 0) {
                i--;
                j--;
            } else if (p.charAt(j) == '*') {
                char temp = p.charAt(j-1);
                while (i >= 0 && s.charAt(i) == temp) i--;
                j = j-2;
                while (j>= 0 && p.charAt(j) == temp) j--;
            } else {
                return false;
            }
        }
        if (i == -1 && j == -1) return true;
        if (i == -1 && isMatchNull(p.substring(0, j + 1))) return true;
        return false;
    }

    /**
     * 打败了26% 算麻烦了
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatchDrop(String s, String p) {

        p = trimMulti(p);

        int lengthP = p.length();
        int lengthS = s.length();
        int i = 0;
        int j = 0;
        for (; i < lengthS && j < lengthP; ) {
            if (j < lengthP - 1 && p.charAt(j + 1) == '*') {
                StringBuilder head = new StringBuilder();
                while (i < lengthS && j < lengthP - 1) {
                    String newS = s.substring(i);
                    String newP = head + ((j == lengthP - 2) ? "" : p.substring(j + 2));
                    if (head.length() > newS.length()) break;
                    boolean subResult = isMatch(newS, newP);
                    head.append(p.charAt(j));
                    if (subResult) return true;
                }
                return false;
            } else if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
                i++;
                j++;
                continue;
            } else if (s.charAt(i) != p.charAt(j)) return false;
        }
        if (i == lengthS && j == lengthP) return true;
        if (i == lengthS && isMatchNull(p.substring(j))) return true;
        return false;
    }

    private boolean isMatchNull(String p) {
        int count = 0;
        int length = p.length();
        if (length % 2 == 1) return false;
        for (int i = 0; i < length; i++) {
            if (p.charAt(i) == '*') count--;
            else count++;
        }
        if (count == 0) return true;
        return false;
    }

    private String trimMulti(String p) {
        StringBuilder result = new StringBuilder();
        int length = p.length();
        if (length <= 1) return p;
        char lastChar = ' ';
        for (int i = 0; i < length; i++) {
            if (i < length - 1 && p.charAt(i + 1) == '*') {
                if (i < length - 1 && p.charAt(i) == lastChar) {
                    i++;
                    continue;
                }
                lastChar = p.charAt(i);
                result.append(p.charAt(i));
                result.append("*");
                i++;
                continue;
            } else {
                lastChar = ' ';
            }
            result.append(p.charAt(i));
        }
        return result.toString();
    }

    /**
     * leetcode上的最佳
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatchBest(String s, String p) {
        boolean[] match = new boolean[s.length() + 1];
        match[s.length()] = true;

        for (int i = p.length() - 1; i >= 0; i--) {
            if (p.charAt(i) == '*') {
                for (int j = s.length() - 1; j >= 0; j--) {
                    match[j] = match[j] || //
                            (match[j + 1] && (p.charAt(i - 1) == '.' || p.charAt(i - 1) == s.charAt(j)));
                }
                i--;
            } else {
                for (int j = 0; j < s.length(); j++) {
                    match[j] = match[j + 1] && (p.charAt(i) == '.' || p.charAt(i) == s.charAt(j));
                }
                match[s.length()] = false;
            }
        }

        return match[0];
    }
}
