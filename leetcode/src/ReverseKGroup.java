/**
 * Created by zhangying on 6/5/18.
 */
public class ReverseKGroup {

    /**
     * 打败30.57%...
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        int count = count(head);
        if (k > count || k <= 1) return head;
        int curCount = 1;
        ListNode groupHead = head;
        ListNode index = groupHead;
        ListNode lastGroup = null;
        while (curCount <= count) {
            //移到下一个分组
            if (curCount % k == 0) {
                if (lastGroup != null) lastGroup.next = groupHead;
                lastGroup = index;
                index = index.next;
                groupHead = index;
                curCount++;
                if (count - curCount < (k - 1)) break;
            } else {
                //向前换一个
                ListNode temp = index.next;
                index.next = index.next.next;
                temp.next = groupHead;
                groupHead = temp;
                curCount++;
            }

            PrintUtils.printList(head);
            if (curCount / k == 1 && curCount % k == 0) {
                head = groupHead;
            }
        }
        return head;
    }

    private int count(ListNode head) {
        ListNode temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    /**
     * leetcode上的最佳
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroupBest(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        // 不用计算整体的长度，每次移动前先判断一下，并且顺带把指针移动到下一组的第一个
        while (curr != null && count != k) { // find the k+1 node
            curr = curr.next;
            count++;
        }
        if (count == k) { // if k+1 node is found
            // 用递归的方法，curr会在下一组的第一个位置
            curr = reverseKGroup(curr, k); // reverse list with k+1 node as head
            // head - head-pointer to direct part,
            // curr - head-pointer to reversed part;
            // 移动count次
            while (count-- > 0) { // reverse current k-group:
                ListNode tmp = head.next; // tmp - next head in direct part
                head.next = curr; // preappending "direct" head to the reversed list
                curr = head; // move head of reversed part to a new node
                head = tmp; // move "direct" head to the next node in direct part
            }
            head = curr;
        }
        return head;
    }

}
