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
//        testReverseKGroup();
//        testFindSubstring();
//        testLongestValidParentheses();
//        testSudok();
//        testFirstMissingPositiveNumber();
//        testRainWater();
//        testJumpGame2();
//        testultiplyString();
//        testWildVardMatching();
//        testNQueue();
//        testNQueue2();
//        testSpiral();
//        testJummGame();
//        testMergeInterval();
//        testSpiralMatrix2();
//        testInsertInterval();
        testPermutaionSequence();

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

    private static void testFindSubstring() {
        String s = "barfoothefoobarman";
        String[] words = {"bar", "foo"};
//        String s = "wordgoodstudentgoodword";
//        String[] words = {"word","student"};
//        String s = "wordgoodgoodgoodbestword";
//        String[] words = {"word","good","best","good"};
//        String s = "aaa";
//        String[] words = {"a","b"};

        List<Integer> result = new FindSubstring().findSubstring(s, words);
        new PrintUtils<Integer>().printList(result);
    }


    private static void testLongestValidParentheses() {
        String s = ")()";
        int len = new LongestValidParentheses().longestValidParenthesesOpt(s);
        int lenRight = new LongestValidParentheses().longestValidParentheses(s);
        System.out.println("s: " + s + "  len: " + len + "  lenRight: " + lenRight);

    }

    private static void testSudok() {
        PrintUtils printUtils = new PrintUtils();
//        char[][] data = {
//                        {'5','3','.', '.','7','.', '.','.','.'},
//                        {'6','.','.', '1','9','5', '.','.','.'},
//                        {'.','9','8', '.','.','.', '.','6','.'},
//
//                        {'8','.','.', '.','6','.', '.','.','3'},
//                        {'4','.','.', '8','.','3', '.','.','1'},
//                        {'7','.','.', '.','2','.', '.','.','6'},
//
//                        {'.','6','.', '.','.','.', '2','8','.'},
//                        {'.','.','.', '4','1','9', '.','.','5'},
//                        {'.','.','.', '.','8','.', '.','7','9'}};
        char[][] data = {
                {'.', '.', '9', '7', '4', '8', '.', '.', '.'},
                {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '2', '.', '1', '.', '9', '.', '.', '.'},
                {'.', '.', '7', '.', '.', '.', '2', '4', '.'},
                {'.', '6', '4', '.', '1', '.', '5', '9', '.'},
                {'.', '9', '8', '.', '.', '.', '3', '.', '.'},
                {'.', '.', '.', '8', '.', '3', '.', '2', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '6'},
                {'.', '.', '.', '2', '7', '5', '9', '.', '.'}};
        printUtils.print(data);
        new Sudok().solveSudoku(data);
        printUtils.print(data);
    }

    private static void testFirstMissingPositiveNumber() {
        int[] nums = {1, 1};
        PrintUtils printUtils = new PrintUtils();
        printUtils.print(nums);
        int result = new MissingPositiveNumber().firstMissingPositive(nums);
        System.out.println("result: " + result);
    }

    private static void testRainWater() {
        int[] height = {4, 2, 3};
        PrintUtils printUtils = new PrintUtils();
        printUtils.print(height);
        int result = new RainWater().trap(height);
        System.out.println("result: " + result);
    }

    private static void testJumpGame2() {
        int[] data = {1, 2, 3};
        PrintUtils printUtils = new PrintUtils();
        printUtils.print(data);
        int result = new JumpGame2().jump(data);
        System.out.println("result: " + result);

    }

    private static void testultiplyString() {
        new MultiplyString().multiply("123", "456");
    }

    private static void testWildVardMatching() {
//        String s = "aa";
//        String p = "*";

//        String s = "acdcb";
//        String p = "a*c?b";
//        String s = "mississippi";
//        String p = "m??*ss*?i*pi";
        String s = "adceb";
        String p = "*a*b";
//        String s = "aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaabaa";
//        String p = "a*******ba";
//        String s = "babbbbaabababaabbababaababaabbaabababbaaababbababaaaaaabbabaaaabababbabbababbbaaaababbbabbbbbbbbbbaabbb";
//        String p = "b**bb**a**bba*b**a*bbb**aba***babbb*aa****aabb*bbb***a";
        System.out.println("s: " + s);
        System.out.println("p: " + p);
        System.out.println("result: " + new WildcardMatching().isMatch(s, p));
    }

    private static void testNQueue() {
        List<List<String>> result = new NQueen().solveNQueens(5);
        new PrintUtils<>().printStringListList(result);
    }

    private static void testNQueue2() {
        int result = new NQueue2().totalNQueens(5);
        System.out.println("result: " + result);
        List<List<String>> resultList = new NQueen().solveNQueens(5);
        new PrintUtils<>().printStringListList(resultList);
    }

    private static void testSpiral() {
        new Spiral().spiralOrder(generateMatrix(3, 6));
    }

    private static int[][] generateMatrix(int row, int column) {
        int[][] matrix = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                matrix[i][j] = (i * column + j + 1);
            }
        }
        return matrix;
    }

    private static void testJummGame() {
        int[] nums = {3,2,1,0,4};
        new PrintUtils().print(nums);
        boolean result = new JumpGame().canJump(nums);
        System.out.println("result: " + result);
    }

    private static void testMergeInterval() {
        new MergeIntervals().test();
    }

    private static void testSpiralMatrix2() {
        int[][] result = new SpiralMatrix2().generateMatrix(0);
        new PrintUtils().print(result);
    }

    private static void testInsertInterval() {
        new InsertInterval().test();
    }
    private static void testPermutaionSequence() {
        System.out.println(new PermutationSequence().getPermutation(4,4));
    }
}
