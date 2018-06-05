import org.w3c.dom.NodeList;

import java.util.List;

/**
 * Created by zhangying on 5/17/18.
 */
public class MainClass {

    public static void main(String[] args) {
//        testPermutations();
//        testPermutaionsAdvanced();
//        testDivide();
//        testFindRange();
//        testCombinationSum();
//        testCombinationSum2();
//        testRotate();
//        testRegularMatching();
//        testMergeKList();
        testReverseKGroup();
    }

    public static void testRoman() {
        System.out.print(new Roman().intToRoman(1994));
    }

    public static void testPermutations() {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = new Permutations().permute(nums);
        for (List<Integer> list : result) {
            for (int item : list) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }

    public static void testPermutaionsAdvanced() {
        int[] nums = {0, 1, 0, 0, 9};
        List<List<Integer>> result = new PermutationsAdvanced().permuteUnique(nums);
        for (List<Integer> list : result) {
            for (int item : list) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }

    public static void testQuickSort() {
        int[] nums = {98, 45, 23, 6, 7, 34, 3, 6, 5, 22};
        new Sort().quickSort(nums, 0, nums.length - 1);
        for (int i : nums) {
            System.out.print(i + " ");
        }
    }

    public static void testDivide() {
        int devidend = -2147483648;
        int divisor = -1;
        System.out.println("devidend: " + devidend + " divisor: " + divisor);
        System.out.println("result: " + new Divide().divide(devidend, divisor));
        if (devidend != 0 && divisor != 0) {
            System.out.println("systemResult: " + (devidend / divisor));
        }
    }

    public static void testFindRange() {
        int[] data = {1};
        int target = 1;
        int[] result = new FindRange().searchRange(data, target);
        System.out.println("result: " + result[0] + " , " + result[1]);

    }

    public static void testCombinationSum() {
        int[] data = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> result = new CombinationSum().combinationSum(data, target);
        new PrintUtils<Integer>().print(result);
    }

    public static void testCombinationSum2() {
        int[] data = {2, 5, 2, 1, 2};
        int target = 5;
        List<List<Integer>> result = new CombinationSum2().combinationSum2Best(data, target);
        new PrintUtils<Integer>().print(result);
    }

    public static void testRotate() {
//        int[][] matrix = {{1, 2, 3,}, {4, 5, 6,}, {7, 8, 9}};
//        int [][] matrix = {{1,2,},{3,4}};
//        int[][] matrix = {{1, 2, 3, 4},
//                {5, 6, 7, 8},
//                {9, 10, 11, 12},
//                {13, 14, 15, 16}};
        int[][] matrix = generateMatrix(6);
        new PrintUtils().print(matrix);
        new Rotate().rotateOpt(matrix);
        new PrintUtils().print(matrix);
    }

    private static int[][] generateMatrix(int row) {
        int[][] matrix = new int[row][row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                matrix[i][j] = (i * row + j + 1);
            }
        }
        return matrix;
    }

    public static void testRegularMatching() {
        String s = "ab";
        String p = ".*";
        System.out.println("s: " + s);
        System.out.println("p: " + p);
        System.out.println("result: " + new ExpressionMatching().isMatch(s, p));
    }

    public static void testMergeKList() {
//        int[][] data = {{1, 4, 5},
//                {1, 3, 4},
//                {2, 6}};
//        int[][] data = {{-10,-9,-9,-3,-1,-1,0},{-5},{4},{-8},{},{-9,-6,-5,-4,-2,2,3},{-3,-3,-2,-1,0}};
        int[][] data = {{1, 4, 5}, {1, 3, 4}, {2, 6}};
        ListNode[] list = buildNodeListArray(data);
        printNodeListArray(list);
        printList(new MergetKLists().mergeKListsOpt(list));
    }

    private static ListNode[] buildNodeListArray(int[][] data) {
        int length = data.length;
        ListNode[] resultList = new ListNode[length];
        ListNode[] tempArray = new ListNode[length];
        for (int i = 0; i < length; i++) {
            int iLength = data[i].length;
            if (iLength <= 0) {
                resultList[i] = null;
                continue;
            }
            resultList[i] = new ListNode(data[i][0]);
            tempArray[i] = resultList[i];
            for (int j = 1; j < iLength; j++) {
                tempArray[i].next = new ListNode(data[i][j]);
                tempArray[i] = tempArray[i].next;
            }
        }
        return resultList;
    }

    private static void printNodeListArray(ListNode[] array) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            printList(array[i]);
        }
    }

    private static void printList(ListNode head) {
        ListNode temp = head;
        if (temp == null) {
            System.out.println("[]");
            return;
        }
        while (temp.next != null) {
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }
        System.out.println(temp.val);
    }

    public static void testReverseKGroup() {
        int[] data = {1};
        ListNode head = buildNodeList(data);
        printList(head);
        printList(new ReverseKGroup().reverseKGroup(head, 3));
    }

    private static ListNode buildNodeList(int[] data) {
        int length = data.length;
        if (length <= 0) return null;
        ListNode head = new ListNode(data[0]);
        ListNode temp = head;
        for (int i = 1; i < length; i++) {
            temp.next = new ListNode(data[i]);
            temp = temp.next;
        }
        return head;
    }

}
