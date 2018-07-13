/**
 * Created by zhangying on 7/13/18.
 * https://leetcode.com/problems/partition-list/description/
 */
public class PartitionList {

    /**
     * beats 100%
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode lessXEnd = newHead;
        while (lessXEnd != null && lessXEnd.next != null && lessXEnd.next.val < x)
            lessXEnd = lessXEnd.next;

        ListNode temp = lessXEnd.next;
        ListNode tempAfter;
        ListNode tempPre = lessXEnd;

        while (temp != null) {
            tempAfter = temp.next;
            if (temp.val < x) {
                tempPre.next = temp.next;
                temp.next = lessXEnd.next;
                lessXEnd.next = temp;
                lessXEnd = temp;
            } else {
                tempPre = temp;
            }
            temp = tempAfter;
        }
        return newHead.next;
    }

}
