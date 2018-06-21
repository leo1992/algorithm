/**
 * Created by zhangying on 6/21/18.
 * https://leetcode.com/problems/rotate-list/description/
 */
public class RotateList {

    /**
     * 两次提交通过，没写测试入口
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k <= 0) return head;
        ListNode newHeadSPre = head;
        ListNode newHeadEnd = head;
        int ktemp = k;
        while (k > 0) {
            k--;
            if (newHeadEnd.next != null) newHeadEnd = newHeadEnd.next;
            else {
                newHeadEnd = head;
                k = k % (ktemp - k);
            }
        }
        if (newHeadEnd == newHeadSPre) return head;
        while (newHeadEnd.next != null) {
            newHeadEnd = newHeadEnd.next;
            newHeadSPre = newHeadSPre.next;
        }
        newHeadEnd.next = head;
        head = newHeadSPre.next;
        newHeadSPre.next = null;
        return head;
    }
}
