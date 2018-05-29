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

}
