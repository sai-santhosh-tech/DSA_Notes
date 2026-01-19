NOTE : String + Set and String + MAP

TIME COMPLEXITY FOR MAP AND SET TRAVERSALS : O(1) [BOTH Doesn't contain duplicates maximum charactes that SET nad MAP can store is 52 caracters]
SPACE COMPLEXIXTY WHEN WE USING MAP OR SET IN STRING PROBLEMS : O(1)

it means the maximum number of possible keys is limited, not growing with input size.

Lowercase English letters → 26
Uppercase English letters → 26
Total possible characters → 52
So even if the string is length 1 million, the Set/Map will never store more than 52 keys.
➡️ This is why the SPACE AND TIME complexity becomes O(1), not O(N).


1) Length of Last Word

Input: s = "   fly me   to   the moon  "
Output: 4
Explanation: The last word is "moon" with length 4

After converting to array it looks like this
System.out.println(Arrays.toString(strArray));  //[fly, me, , , to, , , the, moon]

Approach 1 : Using inbuilt functions[ Never do this approach in interview]
			 s=s.trim();
			 String[] strArray = s.split(" ");
			 int length = strArray[strArray.length-1].length();
			 
			 Time and Space complexity : O(n)
			 
Approach 2 : Using loop

			public int lengthOfLastWord(String s) {
			  int length = 0;

			   for(int i=s.length()-1;i>=0;i--){
					if(length > 0 && s.charAt(i) == ' ') break;
					
					if(s.charAt(i) != ' ') length++;
			   }

			   return length;
			}
			Tim Complexity : O(n) and Space Complexity : O(1)
			
2) Find Words Containing Character
Approach 1 : Using contains() built-in method
			public List<Integer> findWordsContaining(String[] words, char x) {
				List<Integer> result = new ArrayList<>();
				String character = String.valueOf(x);

				for(int i=0;i<words.length;i++){
					if(words[i].contains(character))
						result.add(i);
				}

				return result;
			}
			
Approach 2 : Without built-in methods

			public List<Integer> findWordsContaining(String[] words, char x) {
					List<Integer> result = new ArrayList<>();

					for(int i=0;i<words.length;i++){
						String word = words[i];
					   for(int j=0;j<word.length();j++){
								if(word.charAt(j) == x) {
									result.add(i);
									break;
								}
						}
						
					}

					return result;
			}
			Time complexity : O(n * m) [ n => no of words, m => max length of each word]  = O(N2)
			Space Complexity : O(n)[ including output space]
			
3) Jewels and Stones
Approach 1 : Brute Force Approach (using multiple loops)
			 int result = 0;
			for(int i=0;i<stones.length();i++){
				for(int j=0;j<jewels.length();j++){
					if(jewels.charAt(j) == stones.charAt(i)){
						result++;
						break;
					}
						
				}
			}
Approach 2 : Using Set
			Time Complexity : O(n) 
			Space complexity : O(1), As jewels contains only alphabets( Total aplhabest = 52 (26 small + 26 capital))
			In worst case also set contains only 52 characters which is why SPACE COMPLEXITY IS O(1)
			 
			Set<Character> set = new HashSet<>();
			int result =0;
			for(int i=0;i<jewels.length();i++)
                set.add(jewels.charAt(i));

			for(int i=0;i<stones.length();i++){
				if(set.contains(stones.charAt(i)))
					result++;
			}
4) Find Most Frequent Vowel and Consonant
			  Map<Character, Integer> freq =new HashMap<>();
			  Set<Character> vowels = new HashSet<>(List.of('a','e','i','o','u'));
					int maxVowelCount = 0;
					int maxConsonantCount = 0;

					for(int i=0;i<s.length();i++){
						freq.put(s.charAt(i), freq.getOrDefault(s.charAt(i), 0)+1);
					}
					
					for(Map.Entry<Character, Integer > map : freq.entrySet()){
						if(vowels.contains(map.getKey())){
						   maxVowelCount= Math.max(maxVowelCount,map.getValue());
						}else{
							if(map.getValue() > maxConsonantCount)
								maxConsonantCount = Math.max(maxConsonantCount,map.getValue());
						}
					}

					return maxVowelCount + maxConsonantCount;
				}
Time : 
Single pass to count chars → O(N)
Traversing map (max 52 keys) → O(1)
✔ Total = O(N)

Space :
Map can store max 52 keys → O(1)
Set contains 5 vowels → O(1)
✔ Total = O(1) (Good)


		NOTE : WHENEVER WE NEED CHECK BALANCE, USE +1, -1 APPROACH
5) Split a string in balanced strings

Aprroach 1  :
			public int balancedStringSplit(String s) {
				int totalCount = 0;
				int LCount = 0;
				int RCount = 0;

				for(int i=0;i<s.length();i++){
					
					if('L' == s.charAt(i)) LCount++;
					else RCount++;

					if(LCount == RCount){
						LCount = RCount = 0;
						totalCount++;
					}
				}

				return totalCount;
				
			}
Approach 2 : Reducing 3 variables to 2 variables
			int totalCount = 0;
			int temp = 0;

			for(int i=0;i<s.length();i++){
				
				if('R' == s.charAt(i)) temp++;
				else temp--;

				if(temp == 0){
					totalCount++;
				}
			}
			
6) Reverse String II
		public String reverseStr(String s, int k) {

		  if(s.length() <= 1 ) return s;

		
		  char[] str= s.toCharArray();  // O(n)

		  for(int i=0;i<str.length;i=i+(2*k)){   // Jumping by 2k steps
				int left = i;
				int right = Math.min(i + k - 1, str.length - 1);  // Ensures Right should not go out of bound
				// Ex : String = abcdefg  (length = 7, last index = 6,  k = 8
				// i = 0 [ left becomes 0 and right becomes 0+8-1 = 7 Goes OutOfIndex, To ensure this we added Math.min(i+k-1,str.length -1)]
				
				while(left < right){            // Reversing the first k characters in 2k length
					char temp = str[left];
					str[left] = str[right];
					str[right]=temp;
					left++;
					right--;
				}
		  }

		  return new String(str);
    }
	
	Time and Space complexity : O(n)
	
7) Valid Palindrome
Approach 1 : Time and Space complexity : O(n)
		StringBuilder result = new StringBuilder();
        s = s.toLowerCase();
        String reverse = "";

        for(int i=0;i<s.length();i++){
            if(Character.isLetterOrDigit(s.charAt(i))){
                     result = result.append(s.charAt(i));
                     reverse = s.charAt(i) + reverse;
            }
                   
        }
        // String str = result.toString();
        // StringBuilder reverse = result.reverse();

        // int left = 0;
        // int right = result.length()-1;
        // while(left < right){
        //     if(result.charAt(left) != result.charAt(right)){
        //              return false;
        //     }
        //     left++;
        //     right--;   
        // }

        return result.toString().equals(reverse);
        
Approach 2 : Two Pointer [ Time Complexity : O(n) and Space Complexity : O(1)] NOT USING EXTRA SPACE HERE
	public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        
        int left = 0;
        int right = s.length()-1;

        while(left < right){
            char leftChar = s.charAt(left);
            char rightChar = s.charAt(right);

            if(!Character.isLetterOrDigit(leftChar)) {
                left++;
            } else if (!Character.isLetterOrDigit(rightChar)) {
                right--;
            } else if (leftChar == rightChar){
                left++;
                right--;
            }else{
                return false;
            }
        }
        
        return true;
    }
	
8) Largest Odd Number in String
	 public String largestOddNumber(String num) {
        
        for(int i=num.length()-1;i>=0;i--){
            if((num.charAt(i) -'0')%2 == 1){
                    return num.substring(0,i+1);
            }   
        }

        return "";
    }
9) Longest Common Prefix

   public String longestCommonPrefix(String[] strs) {
        
        int pointer = 0;

        while(pointer != strs[0].length()){
            char ch = strs[0].charAt(pointer);

            for(int i=1;i<strs.length;i++){
                    if(strs[i].length() == pointer || ch != strs[i].charAt(pointer)){
                        return strs[0].substring(0,pointer);
                    }
            }

            pointer++;
        }

        return strs[0];
        
    }
	//If Test case is  str[0] = "flower", str[1]="fl", str[2]="flow", To handle this kind of testcases added strs[i].length() == pointer condition
	
	Time Complexity : str[0]:6 + str[1] :2+ str[2]:4 = 12 characters, Traversing through 12 characters not whole string everytime
	Sum of length of all strings = O(s) = O(n)
	
10) Valid Anagram

Approach 1 : Time complexity : O(n log n) and Space complexity : O(n)
	if(s.length() != t.length()) return false;

        char[] ch1 = s.toCharArray();
        char[] ch2 = t.toCharArray();

        Arrays.sort(ch1);
        Arrays.sort(ch2);
        int left = 0;
        int right = ch1.length;
        while(left < right){

            if(ch1[left] != ch2[left]) return false;

            left++;
        }

        return true;
Approach 2 : Using Map  Time Complexity : O(n) and Space : O(1)

	public boolean isAnagram(String s, String t) {

        if(s.length() != t.length()) return false;

        Map<Character, Integer> freq = new HashMap<>();

        for(int i=0;i<s.length();i++){
            char ch= s.charAt(i);
            freq.put(ch, freq.getOrDefault(ch, 0)+1);
        }

        for(int i=0;i<t.length();i++){
            char ch = t.charAt(i);

            if(freq.containsKey(ch)) freq.put(ch, freq.get(ch)-1);

            if(!freq.containsKey(ch) || freq.get(ch) < 0) return false;
        }

        return true;

    }
11) Isomorphic Strings

	public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> mapStoT = new HashMap<>();
        Map<Character, Character> mapTtoS = new HashMap<>();

        int x = 0;
        while(x < s.length()){
            Character sch = s.charAt(x);
            Character tch = t.charAt(x);
            if(!mapStoT.containsKey(sch) && !mapTtoS.containsKey(tch)){
                mapStoT.put(sch,tch);
                mapTtoS.put(tch,sch);
            }else if(mapStoT.get(sch) != tch){
                return false;
            }else if(mapTtoS.get(tch) != sch){
                return false;
            }

            x++;
        }

        return true;
        
    }
	
	Ex1 : s = foo, t=bar
	mapStoT = {f -> b, o -> a, o(already added)} mapTtoS ={b -> f, a -> o}
	If already key is present in mapStoT map, Then get the value for it and check whether the current character is also same, I f not same then its ismorphic
	else if(mapStoT.get(sch) != tch) :- To handle this scenario we need to add this condition
	
	Ex2 : s = far, t =  boo
	mapStoT = {f-> b, a -> o, } mapTtoS = { b -> f, o -> a, o(already present)}
	If already key is present in mapTtoS map, Then get the value for it and check whether the current character is also same, I f not same then its ismorphic
	else if(mapTtoS.get(tch) != sch) :- To handle this scenario we need to add this condition
	
	Which is why we need two maps to solve this problem.
	
	
12) Group Anagrams
Approach 1 :

	public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();

        for(String str : strs){
            char[] ch = str.toCharArray();
            Arrays.sort(ch);        // O(k log k)
            String key = new String(ch);

            if(!map.containsKey(key)){
                map.put(new String(key), new ArrayList<>());
                map.get(key).add(str);
            }else{
                 map.get(key).add(str);
				 
            }
        }

       return new ArrayList<>(map.values());
        
    }
	
Let:
n = number of strings
m = maximum length of each string

Time complexity = O(n * m log m),   

You store sorted versions of each string:
Each key uses O(m) space.
Up to n keys in worst case (no anagrams)
Space complexity = O(n * m)

Approach 2 :

	public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();

        for(String str : strs){
            int[] freq = new int[26];  // 26 is nothing but 26 characters

            for(int i=0;i<str.length();i++){
                int index = str.charAt(i) - 'a';  // Getting the Index by subtracting the ascii value for characters
                freq[index]++;
            }

            StringBuilder key = new StringBuilder();

            for(int count : freq){
                key.append("#").append(count); // Generating the key without sorting
            }
			
            String k = key.toString();

            if(!map.containsKey(k)){
                map.put(k, new ArrayList<>());
                map.get(k).add(str);
            }else{
                 map.get(k).add(str);
            }
        }

       return new ArrayList<>(map.values());
        
    }
	
Key Generation :

Create an array of size 26 to count frequency of each letter.
Convert that frequency array into a unique string key (like “#1#0#2…”).
Use this string as a hash key to group anagrams.

Input array : ["eat","tea","tan","ate","nat","bat"]
Keys looks like this = 
eat =>  #1#0#0#0#1#0#0#0#0#0#0#0#0#0#0#0#0#0#0#1#0#0#0#0#0#0
tea =>  #1#0#0#0#1#0#0#0#0#0#0#0#0#0#0#0#0#0#0#1#0#0#0#0#0#0
tan =>  #1#0#0#0#0#0#0#0#0#0#0#0#0#1#0#0#0#0#0#1#0#0#0#0#0#0
ate =>  #1#0#0#0#1#0#0#0#0#0#0#0#0#0#0#0#0#0#0#1#0#0#0#0#0#0
nat =>  #1#0#0#0#0#0#0#0#0#0#0#0#0#1#0#0#0#0#0#1#0#0#0#0#0#0
bat =>  #1#1#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#1#0#0#0#0#0#0

output : [["bat"],["tan","nat"],["eat","tea","ate"]]

⭐ Final Time Complexity
O(N × M)
Where:
N = number of strings
M = Maximum length of each string
This is better than the sorting-based method (O(N × M log M)).n

13) Palindromic Substrings
Approach : Finding Odd length and Even Length PALINDROMES[ expand-around-center technique ] TIME COMPLEXITY : o(n^2)
	public int countSubstrings(String s) {

        if(s.length() == 1) return 1;

        int count=0;
        for(int i=0;i<s.length();i++){
            int oddPalindromesCount = countPalindromes(s,i,i);
            int evenPalindromesCount = countPalindromes(s,i,i+1);
            count = count + oddPalindromesCount + evenPalindromesCount;
        }
        return count;
    }
    public static int countPalindromes(String str, int left, int right){
        int count = 0;

        while(left >= 0 && right < str.length() && (str.charAt(left) == str.charAt(right))){
            count++;
            left--;
            right++;
        }
        return count;
    }

14) Longest Palindrome Substring
Approach : Finding Odd length and Even Length PALINDROMES [ expand-around-center technique ]
	public String longestPalindrome(String s) {

        if(s == null || s.length() < 2) return s;

        int maxLength = 1;
        int startIndex = 0;

        for(int i=0;i<s.length();i++){
           
            // Odd length palindrome
           int len1 = expandFromCenter(s,i,i); 

             // Even length palindrome
           int len2 = expandFromCenter(s,i,i+1);

           int length = Math.max(len1, len2);

           if(length > maxLength){
             maxLength = length;
             startIndex = i -(length-1)/2;
           }
            
        }

        return s.substring(startIndex, startIndex+maxLength);
    }

    public static int expandFromCenter(String str, int left, int right){
		
		
        while(left >= 0 && right < str.length() && (str.charAt(left) == str.charAt(right))){
            left--;
            right++;
        }

        return right-left-1; //👉 When the while loop ends, left and right are already one step OUTSIDE the palindrome.
							// So we must undo that extra expansion.
    }
	
// For Odd length palindrome, We expand from center element with same point(b) "aba" 
// For Even length palindrome, We expand from center but with 2 elements with different positions(b,b), "abba"

Example 1: Odd length palindrome "aba"
expandFromCenter(s, 1, 1);  // center at 'b'
| left | right | chars match? |
| ---- | ----- | ------------ |
| 1    | 1     | b == b ✅     |
| 0    | 2     | a == a ✅     |
| -1   | 3     | ❌ stop       |
//📌 Now: left = -1 and right = 3
But the actual palindrome is from index 0 to 2.
left + 1  → 0
right - 1 → 2
//(right - 1) - (left + 1) + 1 = right - left - 1

