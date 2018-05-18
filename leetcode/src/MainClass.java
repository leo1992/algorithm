import java.util.List;

/**
 * Created by zhangying on 5/17/18.
 */
public class MainClass {

    public static void main(String[] args) {

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

}
