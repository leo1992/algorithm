/**
 * Created by zhangying on 6/26/18.
 * https://leetcode.com/problems/search-a-2d-matrix/description/
 */
public class SearchMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows == 0) return false;
        int columns = matrix[0].length;
        if (columns == 0) return false;
        int start = 0;
        int end = rows * columns - 1;
        while (start <= end) {
            int middle = start + (end - start) / 2;
            if (matrix[middle / columns][middle % columns] == target) return true;
            if (matrix[middle / columns][middle % columns] > target) end = middle - 1;
            else start = middle + 1;
        }
        return false;
    }

}
