/**
 * Created by zhangying on 7/11/18.
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/
 */
public class RemoveDuplicatesFromList2 {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode lastOne = newHead;
        ListNode temp = lastOne.next;
        while (lastOne != null) {
            while (temp != null && temp.next != null && temp.next.val == temp.val) {
                while (temp != null && temp.next != null && temp.next.val == temp.val) {
                    temp = temp.next;
                }
                lastOne.next = temp.next;
                temp = temp.next;
            }
            lastOne = lastOne.next;
            if (temp != null) temp = temp.next;
        }
        return newHead.next;

    }

}
