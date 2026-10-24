/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode traverse = new ListNode(Integer.MIN_VALUE);

        ListNode head = traverse ; 

        while(list1 != null && list2 != null)
        {
           if(list1.val < list2.val )
           {
             traverse.next = list1;
             list1 = list1.next;
           }else {
              traverse.next = list2;
              list2 = list2.next;
           }
           traverse = traverse.next;
        }

        if(list1 != null){
            traverse.next = list1;
            traverse = traverse.next;
        }else {
            traverse.next = list2;
            traverse = traverse.next;
        }
      return head.next;
    }
}