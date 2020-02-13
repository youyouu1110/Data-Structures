package Solution;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {

	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	public ListNode removeElements(ListNode head, int val) {
       
		while(head != null && head.val == val) 
			head = head.next;
		
		if(head == null)
			return null;
		
		ListNode prev = head;
		while(prev.next != null) {
			if(prev.next.val == val) 
				prev.next = prev.next.next;
			else
				prev = prev.next;
		}
		return head;
        
    }
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
