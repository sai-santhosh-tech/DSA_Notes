1) Two Sum II - Input Array Is Sorted
Approach : Two Pointer Approach

	public int[] twoSum(int[] numbers, int target) {
        
        int left = 0;
        int right = numbers.length-1;
        int ans[] = new int[2];

        while(left < right){
            int sum = numbers[left] + numbers[right];

            if(sum > target) right--;
            if(sum < target) left++;
            if(sum == target) {
               ans[0]=left; ans[1]=right;
               break;
            }
        }
        return ans;
    }

2)Is Subsequence
Approach : Two pointer
	public boolean isSubsequence(String s, String t) {

        if(s.isEmpty()) return true;

        int j = 0;

        for(int i=0;i<t.length();i++){
            if((j < s.length()) && s.charAt(j) == t.charAt(i)){
                j++;
            }
        }

        return j == s.length();

    }
	
3)Find the Index of the First Occurrence in String
Approach 1 : Sliding Window  [Time Complexixy => O(n * w)]
	public int strStr(String haystack, String needle) {

       int n = haystack.length();
       int w = needle.length();

       for(int i=0;i<= n-w ;i++){
            int j = 0;
            for(j=0;j<w;j++){
                if(haystack.charAt(j+i) != needle.charAt(j)) break;
            }
        
           if(j == w) return i;
       }

       return -1;
        
    }
Approach 2 : Knuth-Morris-Pratt [ KMP Algorithm ] (String Searching Algorithm)

In this Algorithm, We will finding the Longest Prefix that is also Suffix in a string
Let suppose, haystack = onionionsky and needle = onions

We need to check whether needle is substring of haystack

Step 1 : Building LPS Array for needle [Longest Prefix that also Suffix]

For onions, LPS = [0,0,0,1,2,0]

Step 2 : Checking it in haystack

	public int strStr(String haystack, String needle) {
       int[] lps= new int[needle.length()];
       int n = haystack.length();
       int m = needle.length();
       lps[0]=0; // 1st element in LPS is always 0
       int i=0, j=1;

        while(j < m){
            if(needle.charAt(i) == needle.charAt(j)){
                lps[j]=i+1;
                j++;i++;
            }else{
                if(i==0){
                    lps[j]=0;
                    j++;
                }else{
                    i = lps[i-1]; // Corner case condition [Check for this String : "aabaaac" ]
                }
            }
        }
        i=0; j=0;
        while(i < n){
            if(haystack.charAt(i) == needle.charAt(j)){
                i++;
                j++;
            }else{
                if(j==0){
                    i++;
                }else{
                    j = lps[j-1]; // Checking for LPS position and Nexttime onwards checking from that position instead from starting
                }
            }

            if(j == m) return i - m;
        }

       return -1;
        
    }

Time Complexity => O(n)
Space Complexity => O(n)

4) Container with most water
Approach 1 : Brute Force approach (Time Complexity => O(n^2))
Approach 2 : Two Pointer
	public int maxArea(int[] height) {
		int left =0;
        int right = height.length-1;
        int maxArea = 0;

        while(left < right){
            int area = Math.min(height[left], height[right]) * (right-left);
            maxArea = Math.max(area, maxArea);

            if(height[left] < height[right]){ 
                left++; // Moving the lesser height value
            }else{
                right--;
            }
        }

        return maxArea;
    }

5)3Sum
Approach : 2 sum -Sorted array approach only

 public List<List<Integer>> threeSum(int[] nums) {
        
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        for(int i=0;i<nums.length;i++){
            if(i==0 || nums[i] != nums[i-1]){ // To skip duplicates
                 twoSum(nums, i, result);
            }
        }
        return result;
    }

    public static void twoSum(int[] nums, int x, List<List<Integer>> result){
        int left = x+1;
        int right = nums.length-1;

        while(left < right){
            int sum = nums[left] + nums[right] + nums[x];
            if(sum > 0){
                right--;
            }else if (sum < 0){
                left++;
            }else{
                result.add(Arrays.asList(nums[left], nums[right], nums[x]));
                left++;
                right--;
                while(left < right && nums[left] == nums[left-1]) left++; // To Skip duplicates
            }
        }
    }

Lets Suppose array is [-1, 0, -1, -4, 1, 2, 1, 4, 3, 4, 3]
After Sorting it looks like [-4, -1, -1, 0, 1, 2, 3, 3,4,4]
Here -1, 3, 4 repeats, In order to skip these things we added condition //  if(i==0 || nums[i] != nums[i-1])

Here we are checking -4(x) with remaining array(-1,-1,0,1,2,3,3,4,4)
Here also Remaining array contains duplicates , To skip duplicates we have added conditiong // while(left < right && nums[left] == nums[left-1]) left++;
Lets suppose left points to 3 value in Array, reaches to else condition, now left++, again points 3 only //nums[left] == nums[left-1] means duplicates skipping it.

Time Complaxity :
Sorting => O(n log n) and Loops => O(n ^ 2)
Higher is O(n^2), Hence Time compolexity is O(n^2)

6) Trapping Rain Water
Approach 1 :  Time and Space Complexity => O(n)

	public int trap(int[] height) {

        int n = height.length;
        int[] maxLeft = new int[n];
        int[] maxRight = new int[n];

        maxLeft[0]=height[0];
        for(int i=1;i<n;i++){  // Creating maxLeft array
            maxLeft[i] = Math.max(maxLeft[i-1], height[i]);
        }

        maxRight[n-1]=height[n-1];
        for(int i=n-2;i>=0;i--){ // Creating maxRight array
            maxRight[i]= Math.max(maxRight[i+1], height[i]);
        }

        int ans = 0;

        for(int i=0;i<n;i++){
            int trappedWater = Math.min(maxLeft[i], maxRight[i]) - height[i];
            ans = ans + (trappedWater > 0 ? trappedWater : 0);
        }
        return ans;
    }
	
	Ex : height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
		 maxLeft= [0, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3]
		 maxRight=[3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 1]
		 
		Algorithm = Math.min(maxLeft[i], naxRight[i]) - height[i]
		
Approach 2 : Two pointer 
Time Complexity : O(n) , Space Complexity : O(1)

	public int trap(int[] height) {

        int maxLeft = 0, maxRight =0;
        int left =0, right = height.length-1;
        int water = 0;

        while(left < right){

            if(height[left] <= height[right]){
                
                if(height[left] >= maxLeft){
                    maxLeft = height[left];  
                }else{
                    water = water + maxLeft - height[left];
                }
                left++;
            }else{
                if(height[right] >= maxRight){
                    maxRight = height[right];
                }else{
                    water = water + maxRight - height[right];
                }
                right--;
            }
        }
        return water;
    }

      SUBSTRING = SLIDING WINDOW APPROACH ( MOST OF THE TIMES)
	  
7) Longest Substring Without Repeating Characters

Approach 1 : Sliding Window Approach with two pointers to expand and shrink the window[ Using HashMap ]

	public int lengthOfLongestSubstring(String s) {
        
        Map<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        int i = 0;

        for(int j=0;j<s.length();j++){
            Character ch = s.charAt(j);

            if(map.containsKey(ch) && map.get(ch) >= i){ // Contains duplicate and also duplicate within window [ 2 conditions ]
                i = map.get(ch)+1;  // Moving the window forward
            }
            
			map.put(ch, j);
          
            maxLength = Math.max(maxLength, j-i+1);
        }

        return maxLength;
    }

Approach 2 : Sliding Window Approach with two pointers to expand and shrink the window [ Using HashSet]
	public int lengthOfLongestSubstring(String s) {
        
        Set<Character> set = new HashSet<>();
        int maxLength = 0;
        int i = 0;

        for(int j=0;j<s.length();j++){
            Character ch = s.charAt(j);

            while(set.contains(ch)){
                set.remove(s.charAt(i));
                i++;
            }
            set.add(ch);

            maxLength = Math.max(maxLength, j-i+1);
        }

        return maxLength;
    }

Both approaches have :	
Time Complexity => O(n) and Space Compelxity is O(k) where K is the size of characters

If we need String instead maxLength

		Set<Character> set = new HashSet<>();
        int maxLength = 0;
		int startIdx = 0;
        int i = 0;

        for(int j=0;j<s.length();j++){
            Character ch = s.charAt(j);

            while(set.contains(ch)){
                set.remove(s.charAt(i));
                i++;
            }
            set.add(ch);

           if((j-i+1) > maxLength){
			   maxLength= j-i+1;
			   startIdx = i;
		   }
        }
		System.out.println( s. substring(i , i+maxLength));
		
		
8) Longest Repeating Character Replacement
Approach : Sliding Window Approach with two pointers to expand and shrink the window

Space Complexity : O(1) => Maximum 26 Captial characters it contains

	public int characterReplacement(String s, int k) {

        Map<Character, Integer> map = new HashMap<>();
        int i = 0;
        int maxFreq = 0;
        int longestSubString = 0;

        for(int j=0;j<s.length();j++){
            Character ch = s.charAt(j);

            map.put(ch, map.getOrDefault(ch, 0)+1);

            maxFreq = Math.max(maxFreq, map.get(ch));

            int windowLength = j - i +1;

            if(windowLength - maxFreq > k){
                Character c = s.charAt(i);
                map.put(c,map.get(c)-1);
                i++;
            }

            longestSubString = Math.max(longestSubString, j-i+1);
          
        }

        return longestSubString;
        
    }
Ex :  Try it for this array [ A, A, B, E, A, F, A, A, B, E, A, F]

9) Permutation in String
Example 1:
Input: s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains one permutation of s1 ("ba").

Approach 1 : Sorting + Sliding window

	public boolean checkInclusion(String s1, String s2) {
        char[] ch1 =  s1.toCharArray();
        Arrays.sort(ch1);
        int winLength = s1.length();

        for(int i=0;i<=s2.length()-winLength;i++){
            char[] ch2= s2.substring(i, i+winLength).toCharArray();
            Arrays.sort(ch2);
            int count = 0;
            
                for(int j=0;j<ch1.length;j++){
                    if(ch1[j] == ch2[j]) count++;
                }
            if(count == winLength) return true;
        }

        return false;
        
    }
Time Complexity =>
 n= s2.length(), k = s1.length()
 O(n * k log k)
 
Approach 2 : Array + Sliding Window  [ Time Complexity => O(n) (or) O(n * m) where m is 26]
	public boolean checkInclusion(String s1, String s2) {

        if(s1.length() > s2.length()) return false;
        
        int[] hashString = new int[26];
        int[] hashWindow = new int[26];
        int windowSize = s1.length();
        
        for(int i=0;i<windowSize;i++){
            hashString[s1.charAt(i) - 'a']++;
            hashWindow[s2.charAt(i) - 'a']++;
        }

        int i=0;
        int j=windowSize-1;
        while(j < s2.length()){
            if(isHashSame(hashString, hashWindow)){
                return true;
            }else{
                hashWindow[s2.charAt(i)-'a']--;
                i++;
                j++;
                if(j < s2.length()) hashWindow[s2.charAt(j)-'a']++;
            }
        }
        return false;
    }

    public static boolean isHashSame(int[] hashString, int[] hashWindow){
        for(int i=0;i<26;i++){
            if(hashString[i] != hashWindow[i]){
                return false;
            }
        }
        return true;
    }

10) Sliding Window Maximum
Approach : Using Deque

	public int[] maxSlidingWindow(int[] nums, int k) {
        
        int n = nums.length;
        int[] result = new int[n-k+1];
        Deque<Integer> queue = new ArrayDeque<>();

        int i=0, j = 0;
        while(j < nums.length){

            while(!queue.isEmpty() && nums[j] > queue.peekLast()){ // If Current value is greater than last value in deque
                queue.removeLast(); // Then removing last value
            }
            queue.addLast(nums[j]);

            if(j >= k-1){
                result[i]=queue.peekFirst(); // The front of deque is always the max
                if(queue.peekFirst() == nums[i]) {
					queue.removeFirst(); // Before shifing i , we are checking whether that element is max element, If Yes removing that element
				}
                i++;
            }
            j++;
        }
       
        return result;

    }
| Complexity | Value                                                |
| ---------- | ---------------------------------------------------- |
| Time       | O(n) — each element added & removed at most once |
| Space      | O(k) — deque stores up to k elements             |

11) Fruit into Baskets
Approach : Sliding Window + Hashmap

	public int totalFruit(int[] fruits) {

        Map<Integer,Integer> map = new HashMap<>();
        int maxFruits = 0;

        int i=0;
        
        for(int j=0;j<fruits.length;j++){
            map.put(fruits[j], map.getOrDefault(fruits[j],0)+1);

            while(map.size() > 2){
                int left = fruits[i];
                map.put(left, map.get(left)-1);

                if(map.get(left) == 0){
                    map.remove(left);
                }
                i++;
            }

            maxFruits = Math.max(maxFruits, j-i+1);
        }

        return maxFruits;
    }

//Try out with this example [3,3,3,1,2,1,1,2,3,3,4]  output => 5

12) Longest Substring With At Most K Distinct Characters
	public static void main(String[] args) {
		String str= "abcadcacacaca";
		Map<Character, Integer> map = new HashMap<>();
		int maxSubStr = 0;
		int k = 3;
		int i = 0;
		
		for(int j=0;j<str.length();j++) {
			map.put(str.charAt(j), map.getOrDefault(str.charAt(j), 0)+1);
			
			while(map.size() > k) {
				Character left = str.charAt(i);
				map.put(left, map.getOrDefault(left, 0)-1);
				
				if(map.get(left) == 0) {
					map.remove(left);
				}
				i++;
				
			}
			
			maxSubStr = Math.max(maxSubStr, j-i+1);
		}

		System.out.println(maxSubStr); // output : 11
	}

13) Minimum Size Subarray Sum
Approach : Sliding window
	public int minSubArrayLen(int target, int[] nums) {

        int i=0, j=0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        while(j < nums.length){

            sum = sum + nums[j];

            while(sum >= target){
                minLength = Math.min(minLength, j-i+1);
                sum = sum - nums[i];
                i++;
            }

            j++;
        }
        
        return minLength != Integer.MAX_VALUE ? minLength : 0;
    }

14) Minimum Window Substring
Approach : Sliding Window + Array
	public String minWindow(String s, String t) {

        if(s.length() < t.length()) return "";

        int[] freq= new int[128];

        for(Character ch : t.toCharArray()){
            freq[ch]++;
        }

        int i=0,j=0;
        int count = t.length();
        int minLen = Integer.MAX_VALUE;
        int startIdx = 0;

        while(j < s.length()){
            char jChar = s.charAt(j);

            if(freq[jChar] > 0){
                count--;
            }

            freq[jChar]--;
            j++;

            while(count == 0){
                if(j-i < minLen){
                    minLen = j-i;
                    startIdx = i;
                }

                char iChar = s.charAt(i);
                freq[iChar]++;

                if(freq[iChar] > 0){
                    count++;
                }
                i++;
            }
        }

        return minLen == Integer.MAX_VALUE ?"":s.substring(startIdx, startIdx + minLen);
        
    }

// s = "ADOBECODEBANC", t = "ABC"
//Output: "BANC"
//Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

| Step | right | char | Action           | count | Window     |
| ---- | ----- | ---- | ---------------- | ----- | ---------- |
| 1    | 0     | A    | needed → count-- | 2     | "A"        |
| 2    | 1     | D    | not needed       | 2     | "AD"       |
| 3    | 2     | O    | not needed       | 2     | "ADO"      |
| 4    | 3     | B    | needed → count-- | 1     | "ADOB"     |
| 5    | 4     | E    | not needed       | 1     | "ADOBE"    |
| 6    | 5     | C    | needed → count-- | 0     | "ADOBEC" ✅ |
👉 count == 0 → valid window

Shrinking Window (left pointer)
Current window = "ADOBEC" (length 6)
| left | char | Action                    | count | Window |
| ---- | ---- | ------------------------- | ----- | ------ |
| 0    | A    | removing breaks condition | 1     | ❌ stop |

🔹 Continue Expanding

| right | char | Action           | count | Window         |
| ----- | ---- | ---------------- | ----- | -------------- |
| 6     | O    | not needed       | 1     | "DOBECO"       |
| 7     | D    | not needed       | 1     | "DOBECOD"      |
| 8     | E    | not needed       | 1     | "DOBECODE"     |
| 9     | B    | extra B          | 1     | "DOBECODEB"    |
| 10    | A    | needed → count-- | 0     | "DOBECODEBA" ✅ |
🔹 Shrinking Again
| left | char | Action          | count |
| ---- | ---- | --------------- | ----- |
| 1    | D    | remove          | 0     |
| 2    | O    | remove          | 0     |
| 3    | B    | extra B removed | 0     |
| 4    | E    | remove          | 0     |
| 5    | C    | removing breaks | 1 ❌   |
✔ Window = "CODEBA" (length 6)