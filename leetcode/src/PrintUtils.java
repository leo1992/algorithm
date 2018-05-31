import java.util.List;

/**
 * Created by zhangying on 5/29/18.
 */
public class PrintUtils<T> {

    public void print(List<List<T>> result) {
        for (List<T> itemList : result) {
            for (T item : itemList) {
                System.out.print(item.toString() + " , ");
            }
            System.out.println();
        }
    }

    public void print(int[][] data) {
        int row = data.length;
        int column = data[0].length;
        System.out.println("[");
        for (int i = 0; i < row; i++) {
            System.out.print("[");
            for (int j = 0; j < column; j++) {
                System.out.print(data[i][j]);
                if (j != column - 1) System.out.print(" ,");
            }
            System.out.println("]");
        }
        System.out.println("]");
    }

    public void print(int[] data) {
        int length = data.length;
        System.out.print("[");
        for (int i = 0; i < length; i++) {
            System.out.print(data[i]);
            if (i != length - 1) System.out.print(" ,");
        }
        System.out.println("]");
    }

}
