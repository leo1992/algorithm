import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangying on 6/16/18.
 * https://leetcode.com/problems/spiral-matrix/description/
 */
public class Spiral {

    public List<Integer> spiralOrder(int[][] matrix) {
        new PrintUtils().print(matrix);

        List<Integer> result = new ArrayList<>();
        int rowCount = matrix.length;
        if (rowCount == 0) return result;
        int columnCount = matrix[0].length;
        if (columnCount == 0) return result;
        
        for (int margin = 0; margin < (rowCount + 1) / 2 && margin < (columnCount +1) / 2; margin++) {
            for (int i = margin; i < columnCount - margin; i++) result.add(matrix[margin][i]);
            for (int i = margin + 1; i < rowCount - margin; i++) result.add(matrix[i][columnCount - 1 - margin]);
            if (margin * 2 + 1 >= rowCount) break;
            for (int i = columnCount - margin - 2; i >= margin; i--) result.add(matrix[rowCount - 1 - margin][i]);
            if (margin * 2 + 1 >= columnCount) break;
            for (int i = rowCount - margin - 2; i > margin; i--) result.add(matrix[i][margin]);
        }
        new PrintUtils<Integer>().printList(result);
        return result;
    }

}
