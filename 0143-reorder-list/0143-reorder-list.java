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
    public void reorderList(ListNode head) {
        ListNode slow = head; 
        ListNode fast = head;

        while(fast.next != null && fast.next.next != null){
            slow = slow.next ; 
            fast = fast.next.next ; 
        }

        ListNode right = slow.next; 
        slow.next = null; 


        right = reverse(right);


       ListNode  left = head; 


        while(right != null){
            ListNode next1 = left.next; 
            ListNode next2 = right.next; 

            left.next= right; 
            right.next = next1;

            left = next1; 
            right = next2;
        }
    }

    private ListNode reverse (ListNode head){
        ListNode prev = null; 
        ListNode curr = head;

        while(curr != null){
            ListNode newNode = curr.next;
            curr.next = prev; 
            prev = curr; 
            curr = newNode;
        }
        
        return prev;
    }
}