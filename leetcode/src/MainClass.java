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
        testCombinationSum2();
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
        int[] data = {2,5,2,1,2};
        int target = 5;
        List<List<Integer>> result = new CombinationSum2().combinationSum2Best(data, target);
        new PrintUtils<Integer>().print(result);
    }

}
