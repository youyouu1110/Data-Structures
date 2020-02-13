package Solution;

public class Solution2 {

	public class ListNode{
		   ListNode next;
		   int val;
		   ListNode(int val){
		   this.val = val;
			}
		
	}
	public ListNode removeElements(ListNode head, int val) {
	       ListNode dummyHead = new ListNode(-1);
	       dummyHead.next = head;
	       
	       ListNode prev = dummyHead;
	       while(prev.next != null) {
	    	   if(prev.next.val == val)
	    		   prev.next = prev.next.next;
	    	   else
	    		   prev = prev.next;
	       }
	       return dummyHead.next;
    }
}
