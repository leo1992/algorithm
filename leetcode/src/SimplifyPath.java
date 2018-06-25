/**
 * Created by zhangying on 6/22/18.
 * https://leetcode.com/problems/simplify-path/description/
 */
public class SimplifyPath {

    public String simplifyPath(String path) {
        String[] names = path.split("/");
        int size = names.length;
        for (int i = 0; i < size; i++) {
            if (".".equals(names[i])) {
                names[i] = "";
            } else if ("..".equals(names[i])) {
                names[i] = "";
                int j = i - 1;
                while (j >= 0 && names[j].equals("")) j--;
                if (j >= 0) names[j] = "";
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (!"".equals(names[i])) {
                sb.append("/");
                sb.append(names[i]);
            }
        }
        if (sb.toString().equals("")) return "/";
        return sb.toString();
    }

}
