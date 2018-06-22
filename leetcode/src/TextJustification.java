import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangying on 6/22/18.
 * https://leetcode.com/problems/text-justification/description/
 */
public class TextJustification {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int size = words.length;
        int lineCapacity = 0;
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < size; ) {
            int newCap = lineCapacity + words[i].length();
            if (lineCapacity != 0) newCap++;

            if (newCap > maxWidth) {
                result.add(getLine(temp, lineCapacity, maxWidth));
                temp.clear();
                lineCapacity = 0;
            } else {
                temp.add(words[i]);
                lineCapacity = newCap;
                i++;
            }
        }
        int tempSize = temp.size();
        if (tempSize > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tempSize; i++) {
                if (i != 0) sb.append(' ');
                sb.append(temp.get(i));
            }
            for (int j = 0; j < maxWidth - lineCapacity; j++) sb.append(' ');
            result.add(sb.toString());
        }
        return result;
    }

    private String getLine(List<String> list, int lineCapacity, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        if (size == 1) {
            sb.append(list.get(0));
            for (int j = 0; j < maxWidth - lineCapacity; j++) sb.append(' ');
            return sb.toString();
        }
        int extraSpace = (maxWidth - lineCapacity) / (size - 1);
        int bunsCount = (maxWidth - lineCapacity) % (size - 1);
        for (int i = 0; i < size - 1; i++) {
            sb.append(list.get(i));
            for (int j = 0; j < extraSpace + 1; j++) sb.append(' ');
            if (bunsCount > 0) {
                sb.append(' ');
                bunsCount--;
            }
        }
        sb.append(list.get(size - 1));
        return sb.toString();
    }

}
