1) SORTED Increasing order MEANS
	[1,2,3,4,6,7,8] => a[i+1] > a[i]
	
   SORTED Decreasing order MEANS
	[12,9,8,7,5,2,1] => a[i+1] < a[i]
	
   SORTED Non-Decreasing order MEANS Increase order but it can have duplicates
    [1,2,2,3,4,4,5] => a[i+1] > a[i]
	
Non-Increasing or Non-Decreasing means it can have duplicates

2) IN-PLACE means we can do changes on original array, we don't need to create new array.
   
FIND ALL NOTES IN NAMASTE DSA
PROBLEMS :

1) Remove Duplicates from Sorted Array : Use 2 Pointer 

2) Remove Element : Use 2 Pointer

3) Reverse String(Character array) : Use 2 pointer

4) Best Time to Buy and Sell Stock : Try in O(n) complexity
	Input: prices = [7,1,5,3,6,4]
	Output: 5
	Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
	Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

	public int maxProfit(int[] prices) {
		   int maxProfit = 0;
		   int sellPrice = prices[0];
		   int profit=0;
		   for(int i=0;i<prices.length;i++){

				if(prices[i] < sellPrice){
					sellPrice = prices[i];
				}

				if(prices[i] > sellPrice){
					profit = prices[i]-sellPrice;
					if(profit > maxProfit){
						maxProfit = profit;
					}
				}     
				
		   }

		   return maxProfit;
		}
5) Merge Sorted Array
	Input: nums1 = [1,2,3], m = 3 nums2 = [2,5,6], n = 3
	Output: [1,2,2,3,5,6]
	
	Approach 1 : Copy 1st array and apply 2 pointer[Extra space required]
	Time Complexity : O(m+n) and Space Complexity : O(m)
	 
	public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] nums1Copy = Arrays.copyOf(nums1,m);
        int p1 = 0;
        int p2 = 0;
        for(int i=0;i<m+n;i++){
            if( p2 >= n || (p1 < m && nums1Copy[p1] < nums2[p2])){
                nums1[i]=nums1Copy[p1];
                p1++;
            }else{
                nums1[i]=nums2[p2];
                p2++;
            }
        }      
    }
	
	Approach 2 : Inserting the elements by comparing 2 arrays starting from descending order.[ No extra space]
	Time Complexity : O(m+n) and Space Complexity : O(1)
	public void merge(int[] nums1, int m, int[] nums2, int n) {

        int p1 = m-1;
        int p2 = n-1;
		
        for(int i=m+n-1;i>=0;i--){

            if(p2 < 0){
                break;
            }

            if(p1 >=0 && nums1[p1] > nums2[p2]){
                nums1[i]=nums1[p1];
                p1--;
            }else{
                nums1[i]=nums2[p2];
                p2--;
            }
      }
	  
6) Move Zeros : Use 2 Pointer [ Shift No Zero elements to the front of the array and assing zeros to remaining array]
	public void moveZeroes(int[] nums) {

        int pointer = 0;

        for(int i=0;i<nums.length;i++){
            if(nums[i] != 0){
                nums[pointer]=nums[i];
                pointer++;
            }
        }

        for(int i=pointer;i<nums.length;i++){
            nums[i]=0;
        }
        
    }
	
	2nd Approach : Swap the nonZeroElements to the front of the Array by using another pointer ( 2 pointer approach only)
		int nonZeroIndex =0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] != 0){
                int temp=nums[nonZeroIndex];
                nums[nonZeroIndex]=nums[i];
                nums[i]=temp;
                nonZeroIndex++;
            }
        }
7) Max Consecutive Ones

8) Missing Number

	Approach1 : Sort the array and check condition like nums[i] = nums[i-1]+1 (Is the next number is +1 to previous number)
	Time complexity for sorting in worst case is O(n logn)
	
	Approach 2 : Use Math formula and find the total sum and find the diff between actual sum and total sum to get the missing number
	Time Complexity : O(n)

9) 	Single Number

	Approach1 : Use Hashmap to get the frequency and remove duplicates
	But Time and Space complexity will be O (n)

	Approach2 : Using Bitwise XOR
	XOR works for Single Number but not Duplicate Detection.
	For single number always we should go for XOR, As it doesn't need extra space.
	Space complexity = O(1)
	
	Input: nums = [4,1,2,1,2]
	Output: 4
	 public int singleNumber(int[] nums) {

        int result = 0;

        for(int i=0;i<nums.length;i++){
            result = result ^ nums[i];
        }

        return result;
    }

10) Two Sum : Use HashMap and find the indices for the target
	Input: nums = [2,7,11,15], target = 9
	Output: [0,1]
	Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

11) Contains Duplicates
	Input: nums = [1,2,3,1]
	Output: true
	
	Set<Integer> result = new HashSet<>();
	for(int num : nums){
           if(!result.add(num))return true;
	}
    return false;
	
12) Intersection of Two Arrays

13) Rotate Array : Swap the elements partially
	
	Input: nums = [1,2,3,4,5,6,7], k = 3[ 3 right rotations we have to do]
	Output: [5,6,7,1,2,3,4]
	Explanation:
	rotate 1 steps to the right: [7,1,2,3,4,5,6]
	rotate 2 steps to the right: [6,7,1,2,3,4,5]
	rotate 3 steps to the right: [5,6,7,1,2,3,4]

	public void rotate(int[] nums, int k) {

        int n = nums.length;
        k = k % n; // If k is greater than n, in this case this logic will be helpful
		//Ex : arr=[1,2,3] k = 4 So 4 right rotations we have to do, 
		//Instead we can do k % n, which gives 1 so 1 rotation is enough to get the output whatever we will get even after 4 rotations

        if(k == 0) return; // if k is 0 after modulo, no change needed

        swap(nums, 0, n-k-1); // Reverse the 1st part
        swap(nums, n-k, n-1); // Reverse the 2nd part
        swap(nums, 0, n-1  // Reverse the whole array
        
    }
    public void swap(int[] nums, int left, int right){

        while(left < right){
            int temp = nums[left];
            nums[left]=nums[right];
            nums[right]=temp;
            left++;
            right--;
        }
    }
	
14) Product of Array Except Self : Use Prefix approach

	public int[] productExceptSelf(int[] nums) {
        int n = nums.length;

        int[] prefix = new int[nums.length];
        int[] suffix = new int[nums.length];
        int[] result = new int[nums.length];

        prefix[0] = 1;
        for(int i=1;i<n;i++){
            prefix[i] = prefix[i-1] * nums[i-1];
        }

        suffix[n-1] = 1;
        for(int i=n-2;i>=0;i--){
            suffix[i]= suffix[i+1] * nums[i+1];
        }

        for(int k=0;k<n;k++){
            result[k]=prefix[k] * suffix[k];
        }

        return result;
        
    }
	nums = [1,2,3,4]
	
	Prefix multiplication :
	
	prefix[0] = 1 (nothing on left)
	prefix[1] = nums[0] = 1
	prefix[2] = nums[0] * nums[1] = 1*2 = 2
	prefix[3] = nums[0] * nums[1] * nums[2] = 123 = 6
	
	suffix multiplication :
	suffix[3] = 1 (nothing on right)
	suffix[2] = nums[3] = 4
	suffix[1] = nums[2] * nums[3] = 3*4 = 12
	suffix[0] = nums[1] * nums[2] * nums[3] = 234 = 24
	
15) Majority Element : use Boyer–Moore Voting.

	When you see the same number → +1 vote
    When you see a different number → -1 vote
    When votes drop to zero → change the candidate
    Because the majority element appears more than half the time, it will be the last one standing.
	
16) Maximum Subarray : Kadane's approach

	// Below code works even if array contains only negative numbers
	public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];

        for(int i = 1;i<nums.length;i++){
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(currentSum, maxSum);
        }

        return maxSum;    
    }
	
17) Subarray Sum Equals K
Approach : Prefix Sum + HashMap

	public int subarraySum(int[] nums, int k) {

        Map<Integer, Integer> sumCountMap = new HashMap<>();
        sumCountMap.put(0, 1);

        int prefixSum = 0;
        int count = 0;

       for(int num : nums){
            prefixSum = prefixSum + num;

            int required = prefixSum - k;

            if(sumCountMap.containsKey(required)){
                count = count + sumCountMap.get(required);
            }   

            sumCountMap.put(prefixSum, sumCountMap.getOrDefault(prefixSum, 0)+1);
       }

       return count;
    }

// Try out for this array [2, 3, -5, 5, -5, 1, 4] k=5 output => 
// Why we are adding (0,1) in the Map (Initially)
Picture this array with imaginary position −1
index:  -1 0 1 2 ...
value:   0 a b c ...
prefix sum until index −1 = 0
We mark that once in the map.

✔ Summary
//map.put(0, 1) means prefix sum 0 occurred once initially
//helps count subarrays starting at index 0,  without it, algorithm would be wrong
//it is a common trick in prefix-sum problems

18) Maximum Size Subarray Sum Equals k
Approach : Prefix sum + hashmap

public static void main(String[] args) {
		
		int[] nums= {2, 3, -2, 4, -1, 2 };
		
		int k =5;
		
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, -1); // Just adding 0 at -1 index
		int prefixSum = 0;
		int maxLen = 0;
		
		for(int i=0;i<nums.length;i++) {
			prefixSum += nums[i];
			
			int required = prefixSum - k;
			
			if(map.containsKey(required)) {
				maxLen = Math.max(maxLen, i - map.get(required));
			}
			
			map.put(prefixSum, i);
		}
		
		System.out.println(maxLen);

	}
	//Here In Map we are storing { prefixSum, index }
//   nums = {2, 3, -2, 4, -1, 2 };
// prefix = {2, 5,  3, 7,  6, 8 };
// Map => { {0,-1} {2, 0} {5, 1} {3, 2}...so on }	
