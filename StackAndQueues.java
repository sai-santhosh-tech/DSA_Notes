1) Implement Stack Using Queues

Approach 1 : Pop Costly Approach
Push : O(1), Pop/Peek : O(n), empty : O(1)

class MyStack {

    Queue<Integer> queue1 = new LinkedList<>();
    Queue<Integer> queue2 = new LinkedList<>();

    public void push(int x) {
        queue1.add(x);
    }
    
    public int pop() {
         
         while(queue1.size() > 1){
            queue2.add(queue1.remove());
         }
         int val = queue1.remove();
         while(!queue2.isEmpty()){
            queue1.add(queue2.remove());
         }
         return val;
    }
    
    public int top() {
        
        int top = -1;
        while(queue1.size() > 1){
            queue2.add(queue1.remove());
        }
		 
        top = queue1.peek();
        queue2.add(queue1.remove());

        while(!queue2.isEmpty()){
            queue1.add(queue2.remove());
        } 
        return top;
    }
    
    public boolean empty() {
        return queue1.isEmpty();
    }
}

Approach 2 : Push Costly Approach
Push : O(n), Pop/Peek : O(1), empty : O(1)

	private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();
	

    public void push(int x) {
        q2.add(x);  // Step 1
        // Step 2: Move everything from q1 -> q2
        while (!q1.isEmpty()) {
            q2.add(q1.remove());
        }
        // Step 3: Move everything back to q1, no swap needed
        while (!q2.isEmpty()) {
            q1.add(q2.remove());
        }
    }

    public int pop() {
        if (q1.isEmpty()) return -1;
        return q1.remove();
    }

    public int top() {
        if (q1.isEmpty()) return -1;
        return q1.peek();
    }
	
2) Implement Stack using One Queue

Queue<Integer> queue = new LinkedList<>();
    public void push(int x) {
        queue.add(x);
    }
    
    public int pop() {
         int n = queue.size();
         while(n > 1){
            queue.add(queue.remove());
            n--;
         }
         int val = queue.remove();
         return val;
    }
    
    public int top() {
        int n = queue.size();
        while(n > 1){
            queue.add(queue.remove());
            n--;
         }
         int top = queue.peek();
         queue.remove();
         queue.add(top);

         return top;
    }
3) Implement Queue using Stacks
Approach 1 : add : O(1), pop/peek : o(n)
	Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();
    int length = 0;
    
    public void push(int x) {
        stack1.push(x);
        length++;
    }
    
    public int pop() {

        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        int val = stack2.pop();
        while(!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        length--;
        return val;
    }
    
    public int peek() {

        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        int val = stack2.peek();
        while(!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        return val;
    }
    
    public boolean empty() {
        return length == 0;
    }
Approach 2 : add : O(n), pop/peek : O(1)

	Stack<Integer> stack1 = new Stack();
	Stack<Integer> stack2 = new Stack();
	int length = 0;
	
	public void add(int val) {
		
		while(!stack1.isEmpty()) {
			stack2.push(stack1.pop());
		}
		stack1.push(val);
		while(!stack2.isEmpty()) {
			stack1.push(stack2.pop());
		}
		length++;
	}
	
	public int remove() {
		return stack1.pop();
	}
	
	public int peek() {
		return stack1.peek();
	}
	
4) Valid parentheses = Time and Space Complexity : O(n)

	public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('(',')');
        map.put('{','}');
        map.put('[',']');

        for(int i=0;i<s.length();i++){
            Character bracket = s.charAt(i);
            if(map.containsKey(bracket)){
                stack.push(bracket);
            }else{
                if(stack.isEmpty()) return false;
                Character top = stack.pop();
                if(bracket != map.get(top)) return false;
            }
        }

      return stack.isEmpty();
        
    }
5) Min Stack

Aproach 1 : Using two stacks [ Time Complexity : O(1) and Space Complexity : o(n)]
	Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public void push(int val) {
        stack.push(val);
        if(minStack.isEmpty() || val <= minStack.peek()) minStack.push(val);
    }
    
    public void pop() {
        if(stack.peek().equals(minStack.peek())) minStack.pop();
        stack.pop();
    }
    
    public int top() {
        return stack.isEmpty() ? -1 : stack.peek();
    }
    
    public int getMin() {
        return minStack.isEmpty() ? -1 : minStack.peek();
    }
	
Approach 2 : Using Encoding techique

    private Stack<Integer> stack = new Stack<>();
    private int min;

    public void push(int val) {                                  														//→ push val, set min = val
        if (stack.isEmpty()) {							// If stack is empty
            stack.push(val);							// → push val, set min = val
            min = val;									// If val >= min
        } else if (val >= min) {						// → push val
            stack.push(val);							// If val < min
        } else {										// → push 2*val - min (encoded)
            // encode									// → update min = val
            stack.push(2 * val - min);
            min = val;
        }
    }

    public void pop() {
        int top = stack.pop();                        // If top ≥ min -> Normal top
        if (top < min) {                              // If top < min means this is an encoded value
            // decode previous min				 	  // previous min = 2*min - top ( restore old min)
            min = 2 * min - top;					  
        }
    }

    public int top() {
        int top = stack.peek();                       // If top ≥ min → real value
        return top >= min ? top : min;                // If top < min → actual top is min
    }

    public int getMin() {
        return min;
    }
	
When pushing a value smaller than current min, we encode it so we can restore the old min during pop.

6) Remove Outermost Parentheses
Approach 1 : Using Stack
Time and Space Complexity : O(n)

		Stack stack = new Stack<>();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
                if (stack.size() > 1) ans.append('(');
            } else {
                if (stack.size() > 1) ans.append(')');
                stack.pop();
            }
        }

Approach 2 : Without Stack

Space Complexity depends on StringBuilder, If we are not concating anything then O(1) else O(n)
But here our algorithm is not using any extra space like Stack we did in approach 1.

		StringBuilder str = new StringBuilder();
        int level = 0;

        for(int i=0;i<s.length();i++){
            Character bracket = s.charAt(i);
           if('(' == bracket){
                level++;
                if(level > 1) str.append(bracket);
           }else{
                if(level > 1) str.append(bracket);
                level--;
           }
        }
Approach 3 :  Without Stack

		int balance = 0;
        int prevIdx = 0;
        StringBuilder str = new StringBuilder();

        for(int i=0;i<s.length();i++){
            if('(' == s.charAt(i)) balance++;
            else balance--;

            if(balance == 0){
                if(i > prevIdx+1){
                    str.append(s.substring(prevIdx+1, i));
                }
                prevIdx = i+1;
            }
        }
		
7) Evaluate Reverse Polish Notation

	public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for(int i=0;i<tokens.length;i++){
            if(tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("*") || tokens[i].equals("/")){
                int val2 = stack.pop();
                int val1 = stack.pop();

                switch(tokens[i]){
                    case "+" : 
                            stack.push(val1 + val2);
                            break;
                    case "-" : 
                            stack.push(val1 - val2);
                            break;
                    case "*" : 
                            stack.push(val1 * val2);
                            break;
                    case "/" : 
                            stack.push(val1/val2);
                            break;
                }
               
            }else{
                if(tokens[i] != "")
                  stack.push(Integer.parseInt(tokens[i]));
            }
        }

        return stack.pop();
    }

8) Next Greater Element

Approach : Using Stack [ Will create a map by mapping each element with its next larger element]

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int [] ans = new int[nums1.length];
        Stack<Integer> stack = new Stack<>();
        Map<Integer,Integer> NextGreaterElementsMap = new HashMap<>();
		
		// Pushing -1 for last element[Bcz there is no any next larger element for last array element]
        stack.push(nums2[nums2.length-1]);
        NextGreaterElementsMap.put(nums2[nums2.length-1], -1);

        for(int i=nums2.length-2;i>=0;i--){
            int peek = stack.peek();
                if(peek > nums2[i]){
                    NextGreaterElementsMap.put(nums2[i], peek);
                }else{
                    while(!stack.isEmpty()){
                        peek = stack.peek();
                        if(peek > nums2[i]){
                            NextGreaterElementsMap.put(nums2[i], peek);
                            break;
                        }else{
                            stack.pop();
                        }
                    }

                    if(stack.isEmpty()) {
                        NextGreaterElementsMap.put(nums2[i], -1);
                    }
                }
              stack.push(nums2[i]);
        }

        for(int i=0;i<nums1.length;i++){
            ans[i] = NextGreaterElementsMap.get(nums1[i]);
        }
		return ans;
    }
	
Ex :		 nums2 = [1, 5, 0, 3, 4, 9,  2,  6,  8] 
nextLargerElements = [5, 9, 3, 4, 9, -1, 6, 8, -1]
Map will looks some thing like this : {
	1:5, 5:9, 0:3, 3:4, 4:9, 9:-1, 2:6, 6:8, 8:-1
}

nums1 = [5,3,9,2] so output will be [9,4,-1,6]

Time and Space complexity : O(n)

9) Daily Temperatures
Approach : Using Stack [ Here also we need to find next warmer day]

	public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int ans[] = new int[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(n-1);

        for(int i = n-2;i>=0;i--){
            
            while(!stack.isEmpty()){
               int top = stack.peek();
               if(temperatures[i] >= temperatures[top]){
                    stack.pop();
               }else{
                    ans[i]=top-i;
                    break;
               }
            }

            if(stack.isEmpty()){
                ans[i]=0;
            }

			// Storing indexes
            stack.push(i);
       }

       return ans;
    }
	
	WHENEVER CIRCULAR CONCEPTS COMES EITHER IN ARRAY(OR)LINKEDLIST MAKE THAT ARRAY/LINKEDLIST INTO DOUBLE AND REMAINGING LOGIC WILL REMAINS SAME
10) Next Greater Element - II
Approach 1 : Making Array into Double and applying the Same Next Greater Element Logic

	public int[] nextGreaterElements(int[] nums) {
        int[] ans = new int[nums.length*2];
        Stack<Integer> stack = new Stack<>();
		
		// Doubling the array
        int array[] = new int[nums.length*2];

        for(int i=0;i<nums.length;i++){
            array[i]=nums[i];
            array[nums.length+i]=nums[i];
        }
		// Adding -1 for last element
        ans[ans.length-1]= -1;
		//Pushing the last element
        stack.push(array[array.length-1]);

        for(int i=array.length-2;i>=0;i--){

            while(!stack.isEmpty()){
                int top=stack.peek();
                if(top > array[i]){
                    ans[i]=top;
                    break;
                }else{
                    stack.pop();
                }
            }

            if(stack.isEmpty()) ans[i] = -1;

            stack.push(array[i]);
        }

      return Arrays.copyOfRange(ans,0,nums.length);
    }

Approach 2 : Here We are not doubling the array, Instead we are doubling the indexes
Array remains the same, but algorithm runs 2 times

	public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;

        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();

        ans[ans.length-1]= -1;
        stack.push(nums[nums.length-1]);
		
		// Doubling the indexes
        for(int i=(2 * n)-2;i>=0;i--){

            while(!stack.isEmpty()){
                int top=stack.peek();
                if(top > nums[i%n]){
                    ans[i%n]=top;
                    break;
                }else{
                    stack.pop();
                }
            }

            if(stack.isEmpty()) ans[i%n] = -1;

            stack.push(nums[i%n]);
        }

      return ans;
    }
	
IMPORTANT : Let suppose size = 5;
We loop size*2 times, which is 10 times, If index is 9 => i %n : 9%5 = 4th index it will go

11) Rotting Oranges

Approach : Use Queue to store the rotten indexes.

	public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();

		// First adding all the rotten oranges indexes in the queue
        for(int i=0;i<m;i++){
            for(int j = 0;j<n;j++){
                if(grid[i][j] == 2){
                    queue.add(new int[]{i,j,0});
                }
            }
        }

        int maxMinutes = 0;

		// Marking adjacent oranges are rotten and pushing those rotten oranges again into queue till its empty.
        while(!queue.isEmpty()){
            int[] rottenIndex = queue.remove();
            int x = rottenIndex[0], y = rottenIndex[1], level = rottenIndex[2];
			
			// checking left orange
            if(x > 0 && grid[x-1][y] == 1){
                grid[x-1][y] = 2; //[ If left orange is fresh , making it rotten]
                queue.add(new int[]{x-1, y, level+1}); // Once fruit is rotten, then Adding that to queue
            }
			// checking Right orange
            if(x < m-1 && grid[x+1][y] == 1){
                grid[x+1][y]=2;
                queue.add(new int[]{x+1,y,level+1});
            }
			// checking Top orange
            if(y > 0 && grid[x][y-1] == 1){
                grid[x][y-1]=2;
                queue.add(new int[]{x, y-1, level+1});
            }
			// checking Bottom orange
            if(y < n-1 && grid[x][y+1] == 1){
                grid[x][y+1]=2;
                queue.add(new int[]{x, y+1, level+1});
            }
		// level we are using to track the no of minutes to make whole grid array rotten.
            maxMinutes = Math.max(maxMinutes, level);
        }

		// Finally run over each element and checking if there is any fresh orange, if it is then returning -1
        for(int i=0;i<m;i++){
            for(int j = 0;j<n;j++){
                if(grid[i][j] == 1){
                    return -1;
                }
            }
        }

        return maxMinutes;
        
    }

Time and Space Complexity => O(n2)

12) Design Circular Queue
class MyCircularQueue {

    int[] queue;
    int front;
    int rear;
    int size;

    public MyCircularQueue(int k) {
        this.size = k;
        this.queue = new int[size];
        this.front = -1;
        this.rear = -1;
    }
    
    public boolean enQueue(int value) {
        if(isFull()) return false;

        if(front == -1) front = 0;

        rear = (rear+1)%size;
        queue[rear]=value;
        return true;
    }
    
    public boolean deQueue() {

        if(isEmpty()) return false;

        if(front == rear){
            front=rear=-1;
        }else{
            front = (front+1)%size;
        }
        return true;
        
    }
    
    public int Front() {
        if(front == -1){
            return -1;
        }
        return queue[front];
    }
    
    public int Rear() {
        if(rear == -1){
            return -1;
        }
        return queue[rear];
    }
    
    public boolean isEmpty() {
        return front == -1 && rear == -1;
    }
    
    public boolean isFull() {
        return (rear+1)%size == front;
    }
}

13) First Unique Character in a String
Approach 1 : Storing Character in a Queue
	public int firstUniqChar(String s) {
		int[] freq = new int[26];
        Queue<Character> queue = new LinkedList<>();

        for(int i=0;i<s.length();i++){
            Character ch=s.charAt(i);
            queue.add(ch);
            freq[ch-'a']++;
            while(!queue.isEmpty() && freq[queue.peek()-'a'] > 1){
                     queue.remove();
            }
        }
        
        return queue.isEmpty() ? -1 : s.indexOf(queue.peek())
	}

indexOf(char) scans the string linearly.
👉 Worst-case = O(n) Because in the worst case, the first unique char is near the end.
Because indexOf() itself does another scan after the first scan already processed all characters.
first scan → O(n)
second scan to locate index → O(n)

Hence:
👉 Worst-case — O(n²)
👉 Average — O(n)

Approach 2 : Storing indeces in the Queue
	public int firstUniqChar(String s) {
        int[] freq = new int[26];
        Queue<Integer> queue = new LinkedList<>();

        for(int i=0;i<s.length();i++){
            Character ch=s.charAt(i);
            queue.add(i);
            freq[ch-'a']++;
            while(!queue.isEmpty() && freq[s.charAt(queue.peek())-'a'] > 1){
                        queue.remove();
            }
        }
        
        return queue.isEmpty() ? -1 : queue.peek();
        
    }
No string scan needed.
Lookup is constant time.

| Approach        | Final Lookup   | Worst-Case Time | Space |
| --------------- | -------------- | --------------- | ----- |
| Store Character | `indexOf()`    | ❌ **O(n²)**     | O(n)  |
| Store Index     | No scan needed | ✅ **O(n)**      | O(n)  |

		
13) Online Stock Span [ NEAREST PREVIOUS GREATER PRICE ]
	
		int[] stocks = {100,80,60,70,60,75,85,100};
		int[] span = new int[stocks.length];
		Stack<Integer> stack = new Stack<>();
		
		for(int currIdx=0;currIdx<stocks.length;currIdx++) {
			
			while(!stack.isEmpty() && stocks[stack.peek()] <= stocks[currIdx]) {
				stack.pop();  // remove all previous days whose price is less than or equal to today's price
			}
			
			if(stack.isEmpty()) {
				span[i]=currIdx+1;
			}else {
				span[i]=currIdx-stack.peek();
			}
			
			stack.push(currIdx);
		}
		
		System.out.println(Arrays.toString(span));
	}
	
Output : [1, 1, 1, 2, 1, 4, 6, 8]