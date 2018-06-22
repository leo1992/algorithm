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

    public void printList(List<T> result) {
        for (T item : result) {
            System.out.print(item.toString() + " , ");
        }
    }

    public void printStringList(List<String> result) {
        for (String item : result) {
            System.out.println(item.toString() + ",");
        }
    }

    public void printArray(T[] result) {
        for (T item : result) {
            System.out.print(item.toString() + " , ");
        }
    }

    public void printStringListList(List<List<String>> result) {
        for (List<String> itemList : result) {
            for (String item : itemList) {
                System.out.println(item + " , ");
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

    public void print(char[][] data) {
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

    public static void printList(ListNode head) {
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

}
