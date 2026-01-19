1) Design Linked List

2) Middle of the Linked List
Approach 1: Create a Array and copy all values of linked list into it.
            Once copied, then middle element is (length/2);
			But space complexity is O(n), Bcz we are creating extra array
Approach 2 : slow and fast pointer

3) Reverse Linked List
Approach : Maintain 3 pointers and 4 steps and try to reverse it by using them.

4) Linked List Cycle
Approach 1 : Using HashSet, Time and Space complexity : O(n)
			 
				public boolean hasCycle(ListNode head) {

					Set<ListNode> seenNode = new HashSet<>();
					ListNode temp = head;

					while(temp != null){
						if(seenNode.contains(temp)){
							return true;
						}
						seenNode.add(temp);
						temp=temp.next;
					}

					return false;
				}
			
Approach 2 : slow and fast pointer, Time complexity : 0(n) and Space complexity : O(1)

5) Palindrome LinkedList
Approach 1 : Convert Linked list to Array and check whether array is palindrome
Approach 2 :  
			1) Find the Mid Node
			2) Reverse the second half of Linked List
			3) Check for palindrome
			
6) Intersection of Two Linked Lists

Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
Output: Intersected at '8'
Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
- Note that the intersected node's value is not 1 because the nodes with value 1 in A and B (2nd node in A and 3rd node in B) are different node references. In other words, they point to two different locations in memory, while the nodes with value 8 in A and B (3rd node in A and 4th node in B) point to the same location in memory.

Approach 1 : Brute Force
				Loop through LinkedList1 and compare with each & every element in LinkedList2 ( Time Complexity : O(n2))
Approach 2 : Create Set and store Node references
				Loop through LinkedList1 and check whether same address is there in set(Time & Space complexity : O(n))
				public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
					Set<ListNode> set=new HashSet<>();

					while(headB != null){
						set.add(headB);
						headB=headB.next;
					}

					while(headA != null){
						if(set.contains(headA))
							return headA;
						headA=headA.next;
					}

					return null;

				}
Approach 3 : Using 2 pointers techique (Space complexity : O(1))
				
				public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
				   ListNode nodeA = headA;
				   ListNode nodeB = headB;

				   while(nodeA != nodeB){
						nodeA = (nodeA == null) ? headB : nodeA.next;
						nodeB = (nodeB == null)? headA : nodeB.next;
				   }

				   return nodeA;

				}
				
				a reaches null → jumps to headB //( If aList shorter then it will reach null faster, once null reaches will move bList, Till bList reaches null we will cover the difference in BList)
				b reaches null → jumps to headA // (Till bList reaches null, aList covers the diff)
				// Once bList reaches null, both lists having same length to cover, both will move one by one  at some point they will intersect
				After switching:
					Both now travel the other list.
					They will intersect at the same node shortly:
					a: A1 → A2 → 8 → 4 → 5 → B1 → B2 → B3 → 8 ← intersection			
					b: B1 → B2 → B3 → 8 → 4 → 5 → A1 → A2 → 8 ← intersection
					Both pointers hit node 8 at the same time → return that node.
	
		NOTE : SENTINEL NODE SHOULD COME TO OUR MIND WHEN WE DEALS WITH DELETION IN LINKED LIST
					
7) Remove Linked List Elements
	Approach : using SENTINEL NODE => A sentinel node (also called a dummy node) is a fake node added at the beginning of a 
	linked list to simplify operations—especially deletion at the head.
	
		public ListNode removeElements(ListNode head, int val) {
		   ListNode sentinelNode = new ListNode();
		   sentinelNode.next = head;
		   ListNode previous = sentinelNode;

		   while(previous != null && previous.next != null){
				if(previous.next.val == val){
					previous.next=previous.next.next;
				}else{
					previous=previous.next;
				}
		   }

		   return sentinelNode.next; ( returning new Head)
		}
8) Remove Nth Node from end of list

Approach 1 [Two Pass Soluction] : 
			public ListNode removeNthFromEnd(ListNode head, int n) {
				int length = 0;
				ListNode sentinelNode = new ListNode();
				sentinelNode.next=head;
				ListNode temp = sentinelNode;

				while(temp.next != null){
					length++;
					temp=temp.next;
				}

				int previousNodePos = length - n;
				temp = sentinelNode;
				length = 0;

				while(temp != null && temp.next != null){
					if(previousNodePos == length){
						temp.next=temp.next.next;
						break;
					}
					temp=temp.next;
					length++;
				}

				return sentinelNode.next;
			}
Approach 2 [One Pass soluction] :
			 public ListNode removeNthFromEnd(ListNode head, int n) {
					ListNode sentinelNode = new ListNode();
					sentinelNode.next=head;
					ListNode first = head;
					ListNode second = sentinelNode;
					int count = 1;

					while(first.next != null){
						if(count >= n){
							 second=second.next; // maintain n distance
						}
						first=first.next;
						count++;
					}

					second.next = second.next.next;
					return sentinelNode.next;
				}
				
				Before count < n:
					Only first moves.
				After count >= n:
					Both move → maintaining n distance.
				By the time first reaches last node, 👉 second is exactly at the node before the node to delete.
				
				This is a variation of the two-pointer approach, where:
				1) first moves until the end
				2) second starts moving only when the gap between first and second becomes n nodes
				This creates the required n-node gap needed to locate the node before the one to delete.
				
9) Remove Duplicates from sorted list
		public ListNode deleteDuplicates(ListNode head) {
			ListNode current = head;
			
			while(current  != null && current.next != null){
				if(current.val == current.next.val){
					current.next =current.next.next;
				}else{
					current=current.next;
				}
			}
			return head;
		}
		
10) Odd Even Linked List
		public ListNode oddEvenList(ListNode head) {

			if(head == null || head.next == null) return head;
			
			ListNode oddList = head;
			ListNode evenList = head.next;
			ListNode evenStart = evenList;

			while(oddList.next != null && evenList.next != null ){
					oddList.next=oddList.next.next;
					evenList.next=evenList.next.next;
					oddList=oddList.next;
					evenList=evenList.next;
			}

			oddList.next=evenStart;

			return head;
		}
		
11) Add Two Numbers
	Approach : Just try to add the numbers from starting from both the lists and 
	if any carry forward value is there[carry = sum/10;], then try to add it in next loop[int sum = carry;].
	If two lists are having different length then in that case take default value in null case[sum = ((l1 != null) ? l1.val : 0) + ((l2 != null) ? l2.val : 0) + sum;]
	If two lists ends but carry is holding some value , in this we need to add that carry value to the end of result list.
	
		 public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
			ListNode head = null;
			ListNode tail = null;
			int carry = 0;
			while(l1 != null || l2 != null || carry > 0){
				ListNode newNode = new ListNode();
				int sum = carry;
				sum = ((l1 != null) ? l1.val : 0) + ((l2 != null) ? l2.val : 0) + sum;
				int digit = sum%10;
				carry = sum/10;
			   
				newNode.val=digit;

				if(head == null && tail == null){
					head=newNode;
					tail=newNode;
				}else{
					tail.next=newNode;
					tail=newNode;
				}

				if(l1 != null)
					l1 = l1.next;
				
				if(l2 != null)
					l2 = l2.next;
			}

			return head;
        }
12) Merge Two Sorted Lists [Approach : Try to find next smaller element, First smaller element will be start point]
Without Dummy Node : We have write one if condition in order to find the starting point.
With Dummy Node : We don't need to add any condition, dummy node will gonna attached to the element which is lesser in while() loop.

Approach 1 [Without dummy Node] :
public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        
        ListNode curr = null;
        if(list1.val < list2.val){
            curr=list1;          // marking as start point
            list1=list1.next;
        }else{
            curr = list2;       // marking as start point
            list2=list2.next;
        }
        ListNode newHead = curr;

        while(list1 != null && list2 != null){
            if(list1.val < list2.val){
                curr.next=list1;
                curr=curr.next;
                list1=list1.next;
            }else{
                curr.next=list2;
                curr=curr.next;
                list2=list2.next;
            }
        }

        if(list1 != null){
            curr=curr.next=list1;
        }else{
            curr.next=list2;
        }
    
		return newHead;
    }

Approach 2 [With Dummy Node] :

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
      ListNode sentinelNode = new ListNode();
      ListNode curr = sentinelNode; // Starting point will be attached to Dummy Node

      while(list1 != null && list2 != null){
        if(list1.val < list2.val){
            curr.next=list1;
            curr=curr.next;
            list1=list1.next;
        }else{
            curr.next=list2;
            curr=curr.next;
            list2=list2.next;
        }
      }

      if(list1 != null){
        curr.next = list1;
      }else{
        curr.next=list2;
      }

      return sentinelNode.next;
    }

13) Rotate List [ Follwed the same 2 pointer approach whatever i did in "Remove Nth Node from end of list" problem]

	public ListNode rotateRight(ListNode head, int k) {

        if(k == 0 || head == null || head.next == null){
            return head;
        }

        int length = 0;
        ListNode curr = head;
        while(curr != null){
            curr=curr.next;
            length++;
        }
        k = k%length;  // To Avoid unnecessary rotations when NoOfRotations > length
		
        ListNode first = head;
        ListNode second = head;
        for(int i=0;i<k;i++){
            first=first.next;
        }

        while(first.next != null){
            second=second.next;
            first=first.next;
        }

        first.next=head;
        ListNode newHead = second.next;
        second.next=null;
        

        return newHead;
    }
	
14) Swap Nodes in Pairs
	public ListNode swapPairs(ListNode head) {

        if(head == null || head.next ==  null ) {
            return head;
        }
        ListNode dummy = new ListNode();
        ListNode prev = dummy;
        ListNode curr = head;
        ListNode next = head.next;

        while(curr != null && next != null){
            prev.next=next;
            curr.next=next.next;
            next.next=curr;

            prev=curr;
            curr=prev.next;
            if(curr != null) next = curr.next;
        }

        return dummy.next;
    }

15) Linked List Cycle II
STEP 1: Detect if a cycle exists (slow & fast pointers)
STEP 2: Find the cycle starting node
Once slow and fast meet:
Move slow back to head
Move both slow and fast one step at a time
They will meet at the cycle start

16) Reverse Linked List II
    public ListNode reverseBetween(ListNode head, int left, int right) {

        if(head == null || head.next == null || left == right ) return head;

        ListNode prev = null;
        ListNode curr = null;
        ListNode reverseStart = null;

        ListNode dummy = new ListNode();
        dummy.next=head;
        prev=dummy;

		 // Step 1: move prev to node before left
        for(int i=1;i<left;i++){
            prev=prev.next;
        }
        
		// Step 2: reverse from left to right
        reverseStart = prev.next;
        curr=reverseStart.next;

        for(int i=0;i<right-left;i++){
            reverseStart.next=curr.next;
            curr.next=prev.next;
            prev.next=curr;
            curr=reverseStart.next;
        }

        return dummy.next;
        
    }
Approach : 
i) prev → node before the sublist to reverse
ii) reverseStart → first node of the sublist
iii) curr → node that will be moved to front of the reversed part
Each iteration moves curr to the front of the reversed section

1) prev → stays fixed throughout the loop.
	Why? Because prev always points to the node before the sublist.
	We always insert the next node after prev to the front of the reversed sublist, so prev doesn’t move.

2) reverseStart → also stays fixed.
	Why? Because reverseStart is the tail of the reversed sublist.
	After each iteration, the new node (curr) is inserted before reverseStart, so reverseStart remains the tail.
	Its next pointer moves forward each time (reverseStart.next = curr.next) to “skip” the node we just moved.

3) curr → moves forward each iteration.
	curr = reverseStart.next moves to the next node to be reversed.

NOTE : ✅ So only curr moves; prev and reverseStart are anchors:
		i) prev anchors before the reversed section
		ii) reverseStart anchors the tail of reversed section

