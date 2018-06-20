import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangying on 6/19/18.
 * https://leetcode.com/problems/insert-interval
 */


public class InsertInterval {

    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }


    public void test() {
//        int[][] data = {{1, 3}, {6, 9}};
        int[][] data = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        List<Interval> list = generateIntervalList(data);
        list = insert(list, new Interval(4, 8));
        printList(list);
    }

    public List<Interval> generateIntervalList(int[][] data) {
        int size = data.length;
        List<Interval> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (data[i].length < 2) return null;
            list.add(new Interval(data[i][0], data[i][1]));
            System.out.print("[" + data[i][0] + " , " + data[i][1] + "], ");
        }
        System.out.println();
        return list;
    }

    public void printList(List<Interval> list) {
        for (Interval item : list) {
            System.out.print("[" + item.start + " , " + item.end + "], ");
        }
    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        int size = intervals.size();
        if (size == 0) {
            intervals.add(newInterval);
            return intervals;
        }
        int startId = 0;
        while (startId < size && intervals.get(startId).start < newInterval.start) startId++;
        if (startId >= size) {
            // 最后两个合并为一个或直接插入
            mergeTwo(intervals, startId - 1, newInterval);
            return intervals;
        }

        Interval addNode = new Interval();
        if (startId != 0) {
            Interval temp = intervals.get(startId - 1);
            if (temp.end < newInterval.start) {
                addNode.start = newInterval.start;
            } else if (temp.end > newInterval.end) {
                return intervals;
            } else {
                intervals.remove(startId - 1);
                startId--;
                addNode.start = temp.start;
            }
        } else {
            addNode.start = newInterval.start;
        }

        Interval temp = intervals.get(startId);
        while (temp != null)

        {
            if (temp.start > newInterval.end) {
                addNode.end = newInterval.end;
                intervals.add(startId, addNode);
                return intervals;
            }
            intervals.remove(startId);
            if (temp.end >= newInterval.end) {
                addNode.end = temp.end;
                intervals.add(startId, addNode);
                return intervals;
            }
            if (startId >= intervals.size()) temp = null;
            else temp = intervals.get(startId);
        }

        addNode.end = newInterval.end;
        intervals.add(addNode);
        return intervals;

    }

    public void mergeTwo(List<Interval> list, int index, Interval two) {
        Interval one = list.get(index);
        if (one.start > two.start) return;

        if (one.end >= two.end) return;
        if (one.end < two.start) {
            list.add(two);
            return;
        }
        list.remove(index);
        list.add(new Interval(one.start, two.end));
    }
}
