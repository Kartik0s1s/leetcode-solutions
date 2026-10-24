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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        if(head == null) return null; 

        ListNode curr = head ; 
        ListNode prev = null ; 

        int size = 0 ; 
        while(curr != null){
           size++;
           prev = curr;
           curr = curr.next; 
        }  

        if(size == n ){
            return head.next;
        }

        int iteration = size - n ; 

        curr = head ; 
        for(int i = 0 ; i<iteration-1 ; i++){
           curr = curr.next ; 
        }

        curr.next = curr.next.next; 
       
        
    return head;

    }
}