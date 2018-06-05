import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by zhangying on 6/4/18.
 */
public class MergetKLists {

    private int length;

    /**
     * 分治方法 beats 99.43%
     * @param lists
     * @return
     */
    public ListNode mergeKListsOpt(ListNode[] lists) {
        length = lists.length;
        while (length > 1) {
            int i = 0;
            for (; i < length / 2; i++) {
                lists[i] = merge(lists[i], lists[length - 1 - i]);
            }
            length = length - i;
        }
        return length <= 0 ? null : lists[0];
    }

    public ListNode merge(ListNode listOne, ListNode listTwo) {
        if (listOne == null && listTwo == null) return null;
        if (listOne == null) return listTwo;
        if (listTwo == null) return listOne;
        ListNode head = new ListNode(0);
        ListNode temp = head;

        while (listOne != null && listTwo != null) {
            if (listOne.val < listTwo.val) {
                temp.next = listOne;
                listOne = listOne.next;
            } else {
                temp.next = listTwo;
                listTwo = listTwo.next;
            }
            temp = temp.next;
        }
        if (listOne == null && listTwo != null) temp.next = listTwo;
        if (listOne != null && listTwo == null) temp.next = listOne;
        return head.next;
    }

    /**
     * 第一次暴力算法。。。 beats4.05%吧
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        length = lists.length;
        ListNode resultHead = findMin(lists);
        ListNode temp = resultHead;
        while (length > 0 && temp != null) {
            temp.next = findMin(lists);
            temp = temp.next;
            if (length == 1) {
                temp.next = lists[0];
                length--;
            }
        }
        return resultHead;
    }

    public ListNode findMin(ListNode[] list) {
        if (length <= 0) return null;
        ListNode result = list[0];
        int minIndex = 0;
        for (int i = 0; i < length; ) {
            if (list[i] == null) {
                list[i] = list[length - 1];
                length--;
                continue;
            }
            if (result == null || list[i].val < result.val) {
                result = list[i];
                minIndex = i;
            }
            i++;
        }
        if (list[minIndex] != null) list[minIndex] = list[minIndex].next;
        if (list[minIndex] == null && length > 0) {
            list[minIndex] = list[length - 1];
            list[length - 1] = null;
            length--;
        }
        return result;
    }

    /**
     * leetcode上的最佳算法
     * 原理也是分支，只是用了递归的方式
     * @param lists
     * @return
     */
    public ListNode mergeKListsBest(ListNode[] lists) {
        if(lists.length==0)
            return null;
        return merge(0,lists.length-1,lists);
    }
    public ListNode merge(int i,int j,ListNode[] lists) {
        if(j<i)return null;
        if(i==j)return lists[i];
        int mid=i+(j-i)/2;
        ListNode l= merge(i,mid,lists);
        ListNode r= merge(mid+1,j,lists);
        ListNode dummy =new ListNode(0);
        ListNode runner= dummy;

        while(l!=null && r!=null) {
            if(l.val>r.val) {
                runner.next=r;
                r=r.next;
                runner=runner.next;
            }
            else {
                runner.next=l;
                l=l.next;
                runner=runner.next;
            }
        }
        if(l==null && r==null)
            return dummy.next;
        if(l==null)
            runner.next=r;
        else
            runner.next=l;
        return dummy.next;
    }

    /**
     * leetcode上用堆排序的方法解决的方案，运算时间比较长，但是复杂度是最优的
     * @param lists
     * @return
     */
    public ListNode mergeKListsHeap(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;

        PriorityQueue<ListNode> heap = new PriorityQueue<>(new Comparator<ListNode>() {

            @Override
            public int compare(ListNode o1, ListNode o2) {
                // TODO Auto-generated method stub
                if(o1.val < o2.val) {
                    return -1;
                } else if( o1.val > o2.val) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        for (ListNode node : lists) {
            if (node != null)
                heap.offer(node);
        }

        ListNode head = new ListNode(0);
        ListNode cur = head;
        while(!heap.isEmpty()) {
            ListNode tmp = heap.poll();
            cur.next = tmp;

            if(tmp.next != null) {
                heap.offer(tmp.next);
            }
            cur = cur.next;
            cur.next = null;
        }

        return head.next;
    }
}
