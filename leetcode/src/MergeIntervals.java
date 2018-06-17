import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zhangying on 6/17/18.
 * https://leetcode.com/problems/merge-intervals/description/
 */
public class MergeIntervals {

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
        int[][] data = {{1, 4}, {0, 4}};
        List<Interval> list = generateIntervalList(data);
        list = merge(list);
        printList(list);
    }

    public List<Interval> generateIntervalList(int[][] data) {
        int size = data.length;
        List<Interval> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (data[i].length < 2) return null;
            list.add(new Interval(data[i][0], data[i][1]));
            System.out.print("[" + data[i][0] + "] , [" + data[i][1] + "], ");
        }
        System.out.println();
        return list;
    }

    public void printList(List<Interval> list) {
        for (Interval item : list) {
            System.out.print("[" + item.start + "] , [" + item.end + "], ");
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        if (intervals == null || intervals.size() <= 1) return intervals;
        for (int i = 1; i < intervals.size(); ) {
            Interval last = intervals.get(i - 1);
            Interval cur = intervals.get(i);
            if ((cur.start <= last.end && cur.start >= last.start)) {
                intervals.remove(i - 1);
                intervals.remove(i - 1);
                Interval interval = new Interval();
                interval.start = last.start <= cur.start ? last.start : cur.start;
                interval.end = last.end < cur.end ? cur.end : last.end;
                intervals.add(i - 1, interval);
            } else i++;
        }
        return intervals;
    }

    /**
     * leetcode上最快
     * @param intervals
     * @return
     */
    public List<Interval> mergeBest(List<Interval> intervals) {
        List<Interval> res = new ArrayList<Interval>();
        for(int i = 0; i < intervals.size(); i++){
            isoverlap(intervals.get(i), res);
        }
        return res;
    }
    public void isoverlap(Interval one, List<Interval> res){
        int size = res.size() - 1;
        int min = one.start, max = one.end;
        for(int i = size; i >= 0; i--){
            Interval now = res.get(i);
            if(now.start > one.end || now.end < one.start){
                continue;
            }else{
                min = Math.min(one.start, Math.min(min, now.start));
                max = Math.max(one.end, Math.max(max, now.end));
                res.remove(i);
            }
        }
        res.add(new Interval(min, max));
    }
}
