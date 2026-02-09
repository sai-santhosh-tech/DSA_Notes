Backtracking :
Recursive Algorithmic technique for solving problems incrementally by trying partial solutions and then abandoning them (backtracking) is they fail to satisfy constraints.

In Simple sentence : Exploring all possibility but being smart by abandoning wrong path early.

1) Subsets

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Approach 1 : Recursive
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backTrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backTrack( List<List<Integer>> result, List<Integer> currentList, int[] nums, int start){
        result.add(new ArrayList<>(currentList)); // O(n) , Copying all the elements
        for(int i=start;i<nums.length;i++){
            currentList.add(nums[i]);
            backTrack(result, currentList, nums, i+1);
            currentList.remove(currentList.size()-1);
        }
    }
}

Approach 2 : Iterative approach
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>()); // empty subset

        for (int num : nums) {
            int size = result.size();

            for (int i = 0; i < size; i++) {
                // COPY existing subset
                List<Integer> newSubset = new ArrayList<>(result.get(i));
                newSubset.add(num);
                result.add(newSubset);
            }
        }
        return result;
    }
}

TIME COMPLEXITY REMAINS SAME FOR BOTH APPROACHES
// Total subsets = 2 ^ n
// So 2^3 = 8 subsets for nums given array

//⏱️ Time Complexity => O(n · 2ⁿ)
	//Why?
	//For an array of size n, total subsets = 2ⁿ
	//For each subset, we copy elements into a new list
	//→ copying takes O(k) where k ≤ n
	// the worst case, copying cost ≈ O(n)
//So overall: 2ⁿ subsets × O(n) copy work = O(n · 2ⁿ)
// This is optimal — you cannot do better because the output itself has size 2ⁿ.

//💾 Space Complexity => O(n · 2ⁿ) (including output)
	//Why?
	//You store 2ⁿ subsets
	//Each subset can take up to n elements
	
	
BASIC COMBINATION SOLUCTION

public class Combinations {

	public static void main(String[] args) {
		int[] nums= {1,2,3,4};
		List<List<Integer>> result = new ArrayList<>();
		backTrack(result, new ArrayList<>(), nums, 0);
		System.out.println(result);
	}
	
	private static void backTrack(List<List<Integer>> result, List<Integer> currentList, int[] nums, int start) {
		result.add(new ArrayList<>(currentList));
		
		for(int i=start;i<nums.length;i++) {
			currentList.add(nums[i]);
			backTrack(result, currentList,nums, i+1);
			currentList.remove(currentList.size()-1);
		}
	}

}
input = [1,2,3,4]
output = 2 ^ 4 = 16 combinations
[[], [1], [1, 2], [1, 2, 3], [1, 2, 3, 4], [1, 2, 4], [1, 3], [1, 3, 4], [1, 4], [2], [2, 3], [2, 3, 4], [2, 4], [3], [3, 4], [4]]

2) Combinations
Input: n = 4, k = 2
Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
Explanation: There are 4 choose 2 = 6 total combinations.
Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        combinations(result, new ArrayList<>(), 1, n, k);
        return result;
    }

    private void combinations(List<List<Integer>> result, List<Integer> currentList, int start, int n, int k){

        if(currentList.size() == k){
            result.add(new ArrayList<>(currentList));
            return;
        }
        
        for(int i=start; i <= n;i++){
            currentList.add(i);
            combinations(result,currentList, i+1, n, k);
            currentList.remove(currentList.size()-1);
        }
    }
}

//why  result.add(new ArrayList<>(currentList)); ?
//We can’t add currentList directly because backtracking modifies it. Java stores references, so all stored results would point to the same
// list. Creating a new ArrayList ensures each combination is independent.

//Time Complexity => O(k. n!/k!(n-k)!)
//Where, k = Time taken to copy elements
//	   n!/k!(n-k)! = Mathematical formula to get all combinations with size k elements
	   
//Space Complexity => O(k. n!/k!(n-k)!)
//Where , n!/k!(n-k)! = Storing all combinations
//		k = Each combination has k elements

3)Permutations
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permutations(result, new ArrayList<>(), nums);
        return result;
    }

    private void permutations(List<List<Integer>> result, List<Integer> currentList, int[] nums){
        if(currentList.size() == nums.length){
            result.add(new ArrayList<>(currentList));
        }

        for(int i=0;i<nums.length;i++){
            if(!currentList.contains(nums[i])){
                currentList.add(nums[i]);
                permutations(result, currentList, nums);
                currentList.remove(currentList.size()-1);
            }
        }
    }
}

//⏱️ Time Complexity
//O(n².n!)
//Total permutations of n elements = n!
//copying n elements = O(n)
//At each recursive level, you loop = O(n)

//💾 Space Complexity
//Including output
//O(n.n!)
//n! = You store n! permutations
//n = Each permutation has n elements

4) Subsets II
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // In order bring all duplicates side by side(We sort the array so duplicates are adjacent)
        subsetsWithDuplicates(result, new ArrayList<>(), nums, 0);
        return result;
    }

    public void subsetsWithDuplicates(List<List<Integer>> result, List<Integer> currentList, int[] nums, int start){
        result.add(new ArrayList<>(currentList));
        for(int i = start; i< nums.length;i++){
            if(i > start && nums[i] == nums[i-1]){ // If current element is same as previous element then skip it
                    continue;
            }
            currentList.add(nums[i]);
            subsetsWithDuplicates(result, currentList, nums, i+1);
            currentList.remove(currentList.size()-1);
        }
    }
}
//Time & Space Complexity => O(n · 2ⁿ)

i > start && nums[i] == nums[i-1]
//During backtracking, we skip duplicate elements only when they appear at the same recursion level, ensuring unique subsets while still 
//allowing duplicates in deeper levels.

When nums = [1,2,2,3]

[]
├── [1]
│   ├── [1,2]
│   │   ├── [1,2,2]
│   │   │   └── [1,2,2,3]
│   │   └── [1,2,3]
│   └── [1,3]
├── [2]
│   ├── [2,2]
│   │   └── [2,2,3]
│   └── [2,3]
├── (skip duplicate 2 at same level)
└── [3]

5) Combination Sum

Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backTrack(target, result, new ArrayList<>(), candidates, 0);
        return result;
    }

    private void backTrack(int remainingSum, List<List<Integer>> result, List<Integer> currentList, int[] nums, int start){

        if(remainingSum == 0) {
            result.add(new ArrayList<>(currentList));
            return;
        }

        if(remainingSum < 0) return;

        for(int i=start; i< nums.length;i++){ // Avoid duplicates by start
            currentList.add(nums[i]);
            backTrack(remainingSum-nums[i], result, currentList, nums, i);
            currentList.remove(currentList.size()-1);
        }
    }
}

//Time Complexity :
//In Binary Tree => 2ⁿ represents the maximum number of nodes at depth n.
//Where 2 represents the children and n is the depth of tree

//In this problem, we can have nodes based on the no of elements in the array
//Let suppose num=[2,3,4,5] here four branches will gonna divide, Its like N-TREE

//Depth of tree = target / minimumElementInArray;
//Hence Time Complexity => n ^ (depth of tree) => n^(target/minElement)

6)Combination Sum II
Input: candidates = [10,1,2,7,6,1,5], target = 8
Output: [ [1,1,6], [1,2,5], [1,7], [2,6]]
Each number in candidates may only be used once in the combination.
Note: The solution set must not contain duplicate combinations.

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backTrack(target, result, new ArrayList<>(), candidates,0);
        return result;
    }

    private void backTrack(int remainingSum, List<List<Integer>> result, List<Integer> currentList, int[] nums, int start){
        if(remainingSum == 0){
            result.add(new ArrayList<>(currentList));
            return;
        }

        if(remainingSum < 0) return;

        for(int i=start; i< nums.length;i++){
            if(i > start && nums[i-1] == nums[i]) { // To avoid duplcates
                continue;
            }
            currentList.add(nums[i]);
            backTrack(remainingSum-nums[i], result, currentList, nums, i+1);
            currentList.remove(currentList.size()-1);
            
        }
    }
}

//Time Complexity:
//Sorting takes O(n log n).
//The backtracking explores all possible subsets, leading to a worst-case time complexity of O(2^n).

//Space Complexity:
//O(n) for recursion stack (excluding result storage).


//Combination Sum I → n-ary tree → n^(target/min)
//Combination Sum II → subset tree → 2^n

7) Permutations II
Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.

Input: nums = [1,1,2]
Output: [[1,1,2], [1,2,1],[2,1,1]]

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        backTrack(result, new ArrayList<>(), nums, used);
        return result;
    }

    private void backTrack(List<List<Integer>> result, List<Integer> currentList, int[] nums, boolean[] used){
        if(nums.length == currentList.size()){
            result.add(new ArrayList<>(currentList));
            return;
        }

        for(int i=0;i<nums.length;i++){
            if(used[i]) continue;

            if(i > 0 && nums[i]==nums[i-1] && !used[i-1]) continue;

            used[i]=true;
            currentList.add(nums[i]);
            backTrack(result, currentList, nums, used);
            currentList.remove(currentList.size()-1);
            used[i]=false;
        }
    }
}

[] , used=[F,F,F]
|
├── pick i=0 → 1
│   [1] , used=[T,F,F]
│   |
│   ├── pick i=1 → 1
│   │   [1,1] , used=[T,T,F]
│   │   |
│   │   └── pick i=2 → 2
│   │       [1,1,2] , used=[T,T,T]   ✔
│   |
│   └── pick i=2 → 2
│       [1,2] , used=[T,F,T]
│       |
│       └── pick i=1 → 1
│           [1,2,1] , used=[T,T,T]   ✔
|
├── pick i=1 → 1   🚫 SKIPPED
|    (nums[1]==nums[0] && used[0]==F)
|
└── pick i=2 → 2
    [2] , used=[F,F,T]
    |
    └── pick i=0 → 1
        [2,1] , used=[T,F,T]
        |
        └── pick i=1 → 1
            [2,1,1] , used=[T,T,T]   ✔

| Type                               | Complexity  |
| ---------------------------------- | ----------- |
| **Time**                           |   O(n × n!) |
| **Aux space (excluding output)**   |   O(n)      |
| **Total space (including output)** |   O(n × n!) |


8) Palindrome Partitioning
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backTrack(result, new ArrayList<>(), s);
        return result;
    }

    public void backTrack(List<List<String>> result, List<String> currentString , String remainingString){
        
        if(remainingString.isEmpty()){
            result.add(new ArrayList<>(currentString));
            return;
        }

        for(int i=1;i<=remainingString.length();i++){
            String partitionedString = remainingString.substring(0,i);
            
            if(!isPalindrome(partitionedString)) continue; // If not palindrome , Just prune it at initial stage itself

            currentString.add(partitionedString);
            backTrack(result, currentString, remainingString.substring(i));
            currentString.remove(currentString.size()-1);
        }
    }

    public boolean isPalindrome(String str){
        int left = 0;
        int right = str.length()-1;
        while(left < right){
            if(str.charAt(left) != str.charAt(right)) return false;

            left++;
            right--;
        }
        return true;
    }
}

Time complexity is O(2ⁿ × n²) due to exponential partitions and palindrome checks.
Space complexity is O(n) auxiliary, and O(2ⁿ × n) including result storage.

Each node is:
(currentList) | remainingString
([], "aab")
│
├── ["a"] | "ab"
│   │
│   ├── ["a","a"] | "b"
│   │   │
│   │   └── ["a","a","b"] | ""
│   │       → ADD TO RESULT
│   │
│   └── ["a","ab"]   ❌ (ab is not palindrome, skipped)
│
├── ["aa"] | "b"
│   │
│   └── ["aa","b"] | ""
│       → ADD TO RESULT
│
└── ["aab"]   ❌ (aab is not palindrome, skipped)

"ab" and "aab" branches are pruned early as they are not palindrome


9) Word Search

class Solution {
    boolean result = false;
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
		
		//Loop through every cell in the board
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j] == word.charAt(0))  // If a cell matches word.charAt(0), start DFS + backtracking
                    backTrack(board, word, i, j, 1);
                    if(result) return true; // If word matches, Then return back, don't loop further
            }
        }

        return result;
    }

    public void backTrack(char[][] board, String word, int x, int y, int nextIndex){

        if(nextIndex == word.length()){
            result = true;
            return;
        }

        char temp = board[x][y];
        board[x][y]='#';     // Prevents revisiting the same cell in the same path.

        if(y < board[0].length-1 && board[x][y+1] == word.charAt(nextIndex)){ // Explore DOWN
            backTrack(board, word, x, y+1, nextIndex+1);
        }

        if(y > 0 && board[x][y-1] == word.charAt(nextIndex)){   // Explore UP
            backTrack(board, word, x, y-1, nextIndex+1);
        }

        if(x < board.length-1 && board[x+1][y] == word.charAt(nextIndex)){ // Explore RIGHT
            backTrack(board, word, x+1, y, nextIndex+1);
        }

        if(x > 0 && board[x-1][y] == word.charAt(nextIndex)){ // Explore LEFT
             backTrack(board, word, x-1, y, nextIndex+1);
        }

        board[x][y]=temp; // RESTORE So other paths can reuse the cell.
    }
}


Board => 
A B C E
S F C S
A D E E

Word => "ABCCED"

After match firstCharacter, we are callng backTrack from nextIndex onwards

Each node represents:
(x, y, nextIndex, charMatched)
Start: exist()
│
└── backTrack(0,0,1)   // matched 'A'
    │
    ├── Right → (0,1,'B')
    │   └── backTrack(0,1,2)
    │       │
    │       ├── Right → (0,2,'C')
    │       │   └── backTrack(0,2,3)
    │       │       │
    │       │       ├── Down → (1,2,'C')
    │       │       │   └── backTrack(1,2,4)
    │       │       │       │
    │       │       │       ├── Down → (2,2,'E')
    │       │       │       │   └── backTrack(2,2,5)
    │       │       │       │       │
    │       │       │       │       └── Left → (2,1,'D')
    │       │       │       │           └── backTrack(2,1,6)
    │       │       │       │               → ✅ FOUND WORD
    │       │       │       │
    │       │       │       └── Other directions ❌
    │       │       │
    │       │       └── Other directions ❌
    │       │
    │       └── Other directions ❌
    │
    └── Other starting directions ❌
	
🔹 What this solution does (high level)
	1) Loop through every cell in the board
	2) If a cell matches word.charAt(0), start DFS + backtracking
	3) From each cell, try 4 directions:
		Right
		Left
		Down
		Up
	4) Mark the cell as visited using '#'
	5) Restore the cell after recursion (backtracking)

Time: O(m × n × 4^L) because we try DFS from every cell and explore up to 4 directions for each character.
Space: O(L) recurson stack.
Where L = length of the Word.[word.length()]