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
    public boolean isPalindrome(ListNode head) {
        
     ListNode slow = head ; 
     ListNode fast = head ; 


    // 1) find mid 
     while (fast != null && fast.next != null){
        slow = slow.next; 
        fast = fast.next.next; 
     }

    // 2) reverse Second Half 
     ListNode secondHalf = reverse(slow);
     
     ListNode firstHalf = head ; 

     // 3) Check Palindrome 
     while(secondHalf != null){
        if(firstHalf.val != secondHalf.val) return false ; 
        secondHalf = secondHalf.next;
        firstHalf = firstHalf.next;
     }
    return true;
    }


    
    private ListNode reverse(ListNode head ){
        ListNode prev = null; 
        ListNode curr = head; 

        while(curr != null){
        ListNode newNode = curr.next; 
        curr.next = prev;
        prev = curr ; 
        curr = newNode;  
        }
        return prev;
    }


}