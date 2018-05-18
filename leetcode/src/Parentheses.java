import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangying on 5/17/18.
 */

public class Parentheses {

    public void run() {
        run(3);
    }

    public void run(int n) {
        generateParenthesis(n);
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        traverse(result, "", 0, 0, n);
        return result;
    }

    public void traverse(List<String> result, String curString, int size, int front, int n) {
        if (curString.length() == 2 * n) {
            result.add(curString);
            return;
        }
        if (front < n) traverse(result, curString + "(", size + 1, front + 1, n);
        if (size > 0) traverse(result, curString + ")", size - 1, front, n);
    }
}
