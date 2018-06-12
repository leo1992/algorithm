/**
 * Created by zhangying on 6/11/18.
 * https://leetcode.com/problems/wildcard-matching/description/
 */
public class WildcardMatching {

    public boolean isMatch(String s, String p) {
        p = trimMulti(p);
        int sLength = s.length();
        if (sLength <= 0 && p.length() <= 0) return true;
        // if (sLength <= 0 || p.length() <= 0) return false;
        boolean[] match = new boolean[sLength + 1];
        match[0] = true;

        for (int i = 0; i < p.length(); i++) {
            boolean isPossiable = false;
            if (p.charAt(i) == '*') {
                int j = 0;
                while (j <= sLength && match[j] == false) j++;
                if (j == s.length() + 1) return false;
                isPossiable = true;
                for (; j <= s.length(); j++) {
                    match[j] = true;
                }
            } else {
                char pC = p.charAt(i);
                for (int j = sLength - 1; j >= 0; j--) {
                    if (match[j] == true && (s.charAt(j) == pC || pC == '?')) {
                        isPossiable = true;
                        match[j + 1] = true;
                    } else {
                        match[j + 1] = false;
                    }
                }
                match[0] = false;
            }
            if (!isPossiable) return false;
        }
        return match[sLength];
    }

    public boolean isMatchDropTwo(String s, String p) {
        p = trimMulti(p);
        String[] subs = p.split("\\*");
        char[] sC = s.toCharArray();
        int sourceOffset = 0;
        if (p.charAt(0) != '*') {
            sourceOffset = indexOf(sC, sourceOffset, subs[0].toCharArray());
            if (sourceOffset != 0) return false;
            sourceOffset += subs[0].length();
        }
        int i = 1;
        int subsSize = subs.length;
        while (sourceOffset != -1 && i < subsSize) {
            sourceOffset = indexOf(sC, sourceOffset, subs[i].toCharArray());
            if (sourceOffset == -1) return false;
            sourceOffset += subs[i].length();
            i++;
        }
        if (p.charAt(p.length() - 1) == '*') return true;
        if (sourceOffset == s.length()) return true;
        return false;
    }

    int indexOf(char[] source, int sourceOffset, char[] target) {

        char first = target[0];
        int sourceCount = source.length - 1 - sourceOffset;
        int targetCount = target.length;
        int max = sourceOffset + (sourceCount - targetCount);

        for (int i = sourceOffset; i <= max; i++) {
            /* Look for first character. */
            if (source[i] != first && first != '?') {
                while (++i <= max && source[i] != first) ;
            }

            /* Found first character, now look at the rest of v2 */
            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = 1;
                     j < end &&
                             (source[j] == target[k] || target[k] == '?');
                     j++, k++)
                    ;

                if (j == end) {
                    /* Found whole string. */
                    return i - sourceOffset;
                }
            }
        }
        return -1;
    }


    public boolean isMatchDrop(String s, String p) {
        p = trimMulti(p);
        int lengthP = p.length();
        int lengthS = s.length();
        int i = 0;
        int j = 0;
        for (; i < lengthS && j < lengthP; ) {
            if (p.charAt(j) == '*') {
                while (i < lengthS) {
                    String newS = s.substring(i);
                    boolean subResult = isMatch(newS, p.substring(j + 1));
                    if (subResult) return true;
                    i++;
                }
                j++;
                break;
            } else if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                i++;
                j++;
                continue;
            } else if (s.charAt(i) != p.charAt(j)) return false;
        }
        if (i == lengthS && j == lengthP) return true;
        if (i == lengthS && isMatchNull(p.substring(j))) return true;
        return false;
    }

    private String trimMulti(String p) {
        int length = p.length();
        if (length <= 1) return p;
        char lastChar = ' ';
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = p.charAt(i);
            if (c == '*' && lastChar == '*') {
                continue;
            } else {
                result.append(c);
                lastChar = c;
            }
        }
        return result.toString();
    }

    private boolean isMatchNull(String p) {
        int length = p.length();
        for (int i = 0; i < length; i++) {
            if (p.charAt(i) != '*') return false;
        }
        return true;
    }

    /**
     * leetCode上最快
     *
     * @param s1
     * @param p1
     * @return
     */
    public boolean isMatchBest(String s1, String p1) {
        char[] s = s1.toCharArray();
        char[] p = p1.toCharArray();
        int i = 0, j = 0;
        int m = s.length, n = p.length;
        int last_match = -1, starj = -1;
        while (i < m) {
            if (j < n && (s[i] == p[j] || p[j] == '?')) {
                i++;
                j++;
            } else if (j < n && p[j] == '*') {
                starj = j;
                j++;
                last_match = i;
            } else if (starj != -1) {
                j = starj + 1;
                last_match++;
                i = last_match;
            } else return false;
        }

        while (j < n && p[j] == '*')
            j++;
        return j == n;
    }

}
