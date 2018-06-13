import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhangying on 6/6/18.
 */
public class FindSubstring {

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        int length = s.length();
        int size = words.length;
        int total = size;
        if (length == 0 || size == 0) return result;

        List<String> dic = new ArrayList<>();
        int[] count = new int[size];
        for (int i = 0; i < size; i++) {
            int index = dic.indexOf(words[i]);
            if (index >= 0) {
                count[index]++;
            } else {
                dic.add(words[i]);
                count[dic.size() - 1] = 1;
            }
        }
        size = dic.size();
        words = new String[size];
        for (int i = 0; i < size; i++) words[i] = dic.get(i);

        char[] sourceChar = s.toCharArray();
        int[] map = new int[length];
        for (int i = 0; i < length; i++) map[i] = -1;
        int step = words[0].length();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < size; j++) {
                char[] curItem = words[j].toCharArray();
                int k = 0, m = i;
                for (; k < step && m < length; k++, m++) {
                    if (curItem[k] != sourceChar[m]) break;
                }
                if (k == step) {
                    map[i] = j;
                    break;
                }
            }
        }

        for (int i = 0; i < length; i++) {
            if (map[i] == -1) continue;
            int[] tempCount = new int[size];
            System.arraycopy(count, 0, tempCount, 0, size);
            int sum = 0;
            for (int j = i; j < length; j += step) {
                if (map[j] >= 0 && tempCount[map[j]] > 0) {
                    tempCount[map[j]]--;
                    sum++;
                } else break;
            }
            if (sum == total) result.add(i);
        }

        return result;
    }

    private boolean found(int[] count, int[][] map, int size, int length, int step, int cur, int sum, int total) {
        if (sum == total) return true;
        if (cur >= length) return false;
        boolean flag = false;
        for (int i = 0; i < size; i++) {
            if (count[i] != 0 && cur + step < length && map[i][cur + step] == step) {
                count[i]--;
                flag = found(count, map, size, length, step, cur + step, sum + 1, total);
                if (!flag) {
                    count[i]++;
                }
            }
        }
        if (!flag) return false;
        return true;
    }


//    public List<Integer> findSubstring(String s, String[] words) {
//        List<Integer> result = new ArrayList<>();
//        int length = s.length();
//        int size = words.length;
//        char[] sourceChar = s.toCharArray();
//        int[][] map = new int[size][length];
//        int[] itemLength = new int[size];
//        for (int i = 0; i < length; i++) {
//            for (int j = 0; j < size; j++) {
//                itemLength[j] = words[j].length();
//                char[] curItem = words[j].toCharArray();
//                int k = 0, m = i;
//                for (; k < itemLength[j] && m < length; k++, m++) {
//                    if (curItem[k] != sourceChar[m]) break;
//                }
//                if (k == itemLength[j]) map[j][m - 1] = k;
//            }
//        }
//
//        boolean[] resultArr = new boolean[length];
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < length; j++) {
//                if (map[i][j] != itemLength[i]) continue;
//                boolean[] hasFound = new boolean[size];
//                hasFound[i] = true;
//                int tempResult = j - itemLength[i] + 1;
//                if (!resultArr[tempResult] && found(hasFound, map, size, length, itemLength, j, 1))
//                    resultArr[tempResult] = true;
//            }
//        }
//        for (int i =0; i<size; i++) if (resultArr[i]) result.add(i);
//        return result;
//    }
//
//    private boolean found(boolean[] hasFound, int[][] map, int size, int length, int[] itemLength, int cur, int count) {
//        if (count == size) return true;
//        if (cur >= length) return false;
//        boolean flag = false;
//        for (int i = 0; i < size; i++) {
//            if (!hasFound[i] && cur + itemLength[i] < length && map[i][cur + itemLength[i]] == itemLength[i]) {
//                hasFound[i] = true;
//                flag = found(hasFound, map, size, length, itemLength, cur + itemLength[i], count + 1);
//                if (!flag) {
//                    hasFound[i] = false;
//                }
//            }
//        }
//        if (!flag) return false;
//        return true;
//    }

//    public List<Integer> findSubstring(String s, String[] words) {
//        int size = words.length;
//        int length = s.length();
//        int[] firstIndex = new int[size];
//        int[] lenthWords = new int[size];
//        for (int i = 0; i < size; i++) {
//            firstIndex[i] = s.indexOf(words[i]);
//            if (words[i] != null) {
//                lenthWords[i] = words[i].length();
//            }
//        }
//        sort(firstIndex, lenthWords);
//
//        PrintUtils printUtils = new PrintUtils();
//        System.out.println("firstIndex");
//        printUtils.print(firstIndex);
//        System.out.println("lenthWords");
//        printUtils.print(lenthWords);
//
//        int i = 0;
//        while (i < size) {
//            for (int j = 0; j< size; j++) {
//
//            }
//        }
//        return null;
//    }
//
//    private void sort(int[] firstIndex, int[] lengthWords) {
//        quickSort(firstIndex, lengthWords, 0, firstIndex.length - 1);
//    }
//
//    private void quickSort(int[] firstIndex, int[] lengthWords, int start, int end) {
//        if (start >= end || start < 0 || end >= firstIndex.length) return;
//        int base = firstIndex[start];
//        int leftIndex = start + 1;
//        int rightIndex = end;
//        while (leftIndex < rightIndex) {
//            while (firstIndex[leftIndex] <= base && leftIndex < rightIndex) leftIndex++;
//            while (firstIndex[rightIndex] >= base && rightIndex > leftIndex) rightIndex--;
//            swap(firstIndex, lengthWords, leftIndex, rightIndex);
//        }
//        if (firstIndex[rightIndex] < base) {
//            swap(firstIndex, lengthWords, start, rightIndex);
//        }
//        quickSort(firstIndex, lengthWords, start, rightIndex - 1);
//        quickSort(firstIndex, lengthWords, leftIndex, end);
//
//    }
//
//    private void swap(int[] firstIndex, int[] lengthWords, int i, int j) {
//        int temp = firstIndex[i];
//        firstIndex[i] = firstIndex[j];
//        firstIndex[j] = temp;
//
//        int tempLength = lengthWords[i];
//        lengthWords[i] = lengthWords[j];
//        lengthWords[j] = tempLength;
//    }

}
