package 链表;

public class _206_反转链表 {
  public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) return head;

    ListNode newHead = reverseList(head.next);

    head.next.next = head;
    head.next = null;

    return newHead;
  }

  public ListNode reverseList2(ListNode head) {
    if (head == null || head.next == null) return head;

    ListNode newHead = null;
    ListNode curr = head;

    while (curr != null) {
      ListNode next = curr.next;
      curr.next = newHead;
      newHead = curr;
      curr = next;
    }

    return newHead;
  }
}
