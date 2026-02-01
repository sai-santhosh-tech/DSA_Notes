Complete Binary Tree:
A Complete Binary Tree is one in which, during level order traversal, there are no missing nodes. 
All levels are completely filled except possibly the last level, which is filled from left to right.

HEAP : It is a Complete Binary Tree.
All the Heaps are complete Binary Tree.

Heaps are two types:

🔹 MAX Heap
Parent ≥ Children
Root contains the maximum element

        50
       /  \
     30    40
    / \
  10  20

🔹 MIN Heap
Parent ≤ Children
Root contains the minimum element
        10
       /  \
     20    30
    / \
  40  50

🔍 Important Notes
Heap is NOT a BST
Only the root is guaranteed to be min/max
Left and right children have no ordering between them.

| Operation      | Time     |
| -------------- | -------- |
| Insert         | O(log n) |
| Delete(root)   | O(log n) |
| Peek (min/max) | O(1)     |
| Build Heap     | O(n)     |

🚀 Common Use Cases
1) Priority Queue
2) Kth smallest / largest element
3) Heap Sort
4) Top K problems
5) Scheduling & Load balancing

Advantages:
Max Heap: If we have to find largest elements inside maxHeap: Time Complexity: O(1) Because it always on root, return root.val
Min Heap: If we have to find smallest elements inside minHeap: Time Complexity: O(1)
Heap Sort: Time Complexity: O(nlogn)
Insert: O(logn)
Delete: O(logn)

🔹 What is Heapify?
Heapify is the process of fixing the heap property in a binary tree (or array) when it is violated.
👉 In simple words:
Heapify = rearrange elements so that heap property is maintained.

1) Heapify Up (Bubble Up)
Used during insertion
📌 Idea:
	a) Compare element with its parent
	b) If heap property is violated → swap
	c) Move upward until fixed

2) Heapify Down (Bubble Down / Percolate Down)
Used during deletion or build heap
📌 Idea:
	a) Compare node with its children
	b) Swap with smaller (min heap) or larger (max heap) child
	c) Move downward until fixed

NOTE => Code for MinPriorityQueue and MaxPriorityQueue will be same as MinHeap and maxHeap

In JAVA, When we create PRIORITY QUEUE, By deafule its MinPriorityQueue

PriorityQueue<Integer> pq = new PriorityQueue<>();

For MaxPriorityQueue , PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());


1) Code to insert and delete from MinHeap(MinPriorityQueue)

public class MinHeap {
	List<Integer> heap = null;
	MinHeap(){
		heap = new ArrayList<>();
	}
	
	public int getLeftChildIndex(int idx) {
		return (2 * idx) + 1;
	}
	public int getRightChildIndex(int idx) {
		return (2 * idx) + 2;
	}
	public int getParentIndex(int idx) {
		return (idx -1)/2;
	}
	
	public void insert(int num) {
		// Adding element at the end
		heap.add(num);
		// Heapify-Up from the last index.
		int lastIndex = heap.size()-1;
		heapifyUp(lastIndex);
	}
	
	public void heapifyUp(int index) {
		while(index > 0) {
			int parentIndex = getParentIndex(index);
			int currValue = heap.get(index);
			int parentValue = heap.get(parentIndex);
			
			if(currValue < parentValue) {
				heap.set(parentIndex, currValue);
				heap.set(index, parentValue);
				index = parentIndex;
			}else {
				break;
			}
		}
	}
	
	public int extract() {
		if(heap.isEmpty()) return -1;
		int minVal = heap.get(0);
		
		// swap last element with 1st element & delete last element
		int lastIndex = heap.size()-1;
		heap.set(0, heap.get(lastIndex));
		heap.remove(lastIndex);
		
		heapifyDown(0);
		
		return minVal;
	}
	
	public void heapifyDown(int i) {
		
		int smallest = i;
		int left = getLeftChildIndex(i);
		int right = getRightChildIndex(i);
		int n = heap.size();
		
		if( left < n && heap.get(left) < heap.get(smallest)) {
			smallest = left;
		}
		
		if(right < n && heap.get(right) < heap.get(smallest)) {
			smallest = right;
		}
		
		if(smallest != i) {
			int temp = heap.get(smallest);
			heap.set(smallest, heap.get(i));
			heap.set(i, temp);
			heapifyDown(smallest);
		}
	}
	
	public int peek() {
		if(heap.isEmpty()) return -1;
		
		return heap.get(heap.size()-1);
	}
	
	public void display() {
		Iterator<Integer> iterator = heap.iterator();
		while(iterator.hasNext()) {
			System.out.print(iterator.next()+" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		MinHeap heap = new MinHeap();
		heap.insert(10);
		heap.insert(20);
		heap.insert(2);
		heap.insert(1);
		heap.insert(5);
		heap.display();
		System.out.println(heap.extract());
		heap.display();
		System.out.println(heap.extract());
		heap.display();
	}
}

2) Kth Largest Element in an Array

Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4

Approach 1: Sorting array ( Time complexity : O(n log n) Space : O(1))
class Solution {
    public int findKthLargest(int[] nums, int k) {
        
        Arrays.sort(nums);
        return nums[nums.length-k];
    }
}

Approach 2 : Using MinHeap(MinPriorityQueue)
class Solution {
    public int findKthLargest(int[] nums, int k) {
        
        Queue<Integer> minPriorityQueue = new PriorityQueue<>();

        for(int num : nums){             // O(n)
            minPriorityQueue.offer(num);  // O(log k)

            if(minPriorityQueue.size() > k){  // At a Moment storing only k maxium k elements in the heap, As soon as size exceeds removing element 
											  // this ensures only last TOP k elements will be there in the heap
                minPriorityQueue.poll();
            }
        }

        return minPriorityQueue.peek(); // picking the k th largest element
    }
}

k = No of Elements
Time Complexity => O(n log k)
Space Complexity => O(k)

3) Kth Largest Element in a Stream

Example :
Input:
["KthLargest", "add", "add", "add", "add", "add"]
[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]

Output: [null, 4, 5, 5, 8, 8]

Explanation:
KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
kthLargest.add(3); // return 4
kthLargest.add(5); // return 5
kthLargest.add(10); // return 5
kthLargest.add(9); // return 8
kthLargest.add(4); // return 8

class KthLargest {

    Queue<Integer> pq = new PriorityQueue<>();
    int k = 0;
    public KthLargest(int k, int[] nums) {
        this.k = k;

        for(int num : nums){
            add(num);
        }
    }
    
    public int add(int val) {
        pq.add(val);
        if(pq.size() > k){
            pq.poll();
        }   
        return pq.peek();
    }
}

Time => O(n log k), Space => O(k)

4) Last Stone Weight

Example 1:

Input: stones = [2,7,4,1,8,1]
Output: 1
Explanation: 
We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of the last stone.

Approach : MaxPriority Queue
class Solution {
    public int lastStoneWeight(int[] stones) {
        Queue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

        for(int stone : stones){
            priorityQueue.offer(stone);
        }

        while(priorityQueue.size() > 1){
            int y = priorityQueue.poll();
            int x = priorityQueue.poll();
            if(y-x > 0){
                 priorityQueue.offer(y-x);
            }   
        }

        return priorityQueue.peek() != null ? priorityQueue.peek() : 0;
        
    }
}
Time complexity => O(n log n), Space => O(n)

5) Top K Frequent Elements
Approach 1 : using Map and Sorting
Time => O(n logn) , Space => O(n)
class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        for(int num :  nums){
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        List<Integer> keys = map.entrySet().stream()
                            .sorted((a,b) -> b.getValue().compareTo(a.getValue()))
                            .map(Map.Entry::getKey)
                            .collect(Collectors.toList());

        for(int i=0;i<k;i++){
            result.add(keys.get(i));
        }

       int[] output = result.stream().mapToInt(Integer::intValue).toArray(); 
       return output;
    }
}

Approach 2 : Using Map and MinPriorityQueue
Time => O(n logk) , Space => O(n)
class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        Queue<Map.Entry<Integer,Integer>> pq = new PriorityQueue<>(
                                         Comparator.comparing(Map.Entry::getValue)); // Based on value maintain min heap
		
		// Creating freq map O(n)
        for(int num :  nums){
            map.put(num, map.getOrDefault(num, 0)+1);
        }
		
		// Add elements to MinPQ and restrict size to K O(n log k)
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            pq.offer(entry);          // O(logk)
            if(pq.size() > k){
                pq.poll();            // O(logk)
            }
        }

        List<Integer> result = new ArrayList<>();
        for(int i=0;i<k;i++){
            result.add(pq.poll().getKey());
        }

       int[] output = result.stream().mapToInt(Integer::intValue).toArray(); 
       return output;
    }
}

6)Kth Smallest Element in a Sorted Matrix

Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.
Example:
Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
Output: 13
Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13

class Solution {

    static class Node{
        int val;
        int row;
        int col;

        Node(int val, int row, int col){
            this.val=val;
            this.row=row;
            this.col=col;
        }

    }
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(node -> node.val));
        int n = matrix[0].length;
      
		// push first element of each row
        for(int i=0;i<n;i++){ 
            pq.offer(new Node(matrix[i][0], i, 0));
        }
		
		// Popping k-1 smallest elements from minpriorityqueue
        for(int i=0;i<k-1;i++){
            Node node = pq.poll();  // popping smallest element
            int row = node.row;
            int col = node.col;

            if(col +1 < n){
                pq.offer(new Node(matrix[row][col+1], row, col+1)); //.pushing next element from the same column until ends
            }
        }

        return pq.peek().val;
    }
}

// [[1,5,9]
//	[10,11,13]
//  [12,13,15]], k = 8
// 1st step pushing  1st element of each row Means 1,10,12 in MinHeap
// 2nd step poping min element and pushing next element from that row until k-1, finally kth smallest element will be present next.

Time Complexity => O(k log n)
Space complexity => O(n)