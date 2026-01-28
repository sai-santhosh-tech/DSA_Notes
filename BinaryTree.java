Tree:
	* Non-linear structure, Hierarchical in nature.
	* Node:
		Can have zero or more children.
		Has only one parent (except root).
	Cannot have cycles: Mean node have only one parent, otherwise it will form cycle (graphs).
	* Exactly one path exists between any two nodes.
	* No two parents can share the same child.

Why Tree Data Structures?
	* Not every type of data can be stored in an array or linked list.
	* When we need to store hierarchical data, we use trees.
		Examples:
		File Systems
		HTML DOM
		Databases
		Hierarchical Data Models
		
Types of Trees:
1) General Tree: Any number of children.
2) Binary Tree: At most two children per node.
3) Binary Search Tree (BST): left < root < right.
4) Complete Binary Tree: All levels filled except possibly the last[Last level must be filled from left to right].
5) Full Binary Tree: Each node has 0 or 2 children.
6) Perfect Binary Tree: Full and all leaves are at the same level.
7) Balanced Binary Tree: Height is log(n).

Binary Tree Traversals :

Pre-Order Traversal
Order: Root → Left → Right

In-Order Traversal
Order: Left → Root → Right

Post-Order Traversal
Order: Left → Right → Root

Level-Order Traversal
Order: Traverse Level by Level (Top to Bottom)

1) Binary Tree Preorder Traversal
Approach 1 : Recursive Approach
	Time: O(n) — each node visited once
	Space: O(n) — result list + recursion stack
	   public List<Integer> preorderTraversal(TreeNode root) {
			List<Integer> result = new ArrayList();
			traversal(root, result);

			return result;
		}
		public static void traversal(TreeNode node, List<Integer> result){
			if(node == null) return;
			result.add(node.val);
			traversal(node.left, result);
			traversal(node.right, result);
		}

Approach 2 : Iterative Approach
	public List<Integer> preorderTraversal(TreeNode root) {

        if(root == null) return new ArrayList<>();

        List<Integer> result = new ArrayList();
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){
            TreeNode curr = stack.pop();
            result.add(curr.val);
            if(curr.right != null ) stack.push(curr.right); // We push right first, so that it will process after left
            if(curr.left != null ) stack.push(curr.left); // We want left to process first which is why adding at last
        }

        return result;
    }
	
2) Binary Tree Inorder Traversal
Approach 1 : Recursive Approach
	Time: O(n) — each node visited once
	Space: O(n) — result list + recursion stack
		public List<Integer> inorderTraversal(TreeNode root) {
			List<Integer> result = new ArrayList<>();
			traversal(root, result);
			return result;
		}
		public void traversal(TreeNode node, List<Integer> result){
			if(node == null) return;
			traversal(node.left, result);
			result.add(node.val);
			traversal(node.right, result);
		}

Approach 2 : Iterative approach

	public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode curr = root;

        while(!stack.isEmpty() || curr != null){
            while(curr != null){
                stack.push(curr);
                curr=curr.left;
            }
            curr = stack.pop();
            result.add(curr.val);
            curr = curr.right;
        }

        return result;
    }
	
3) Binary Tree Postorder Traversal
Approach 1 : Recursive Approach
	Time: O(n) — each node visited once
	Space: O(n) — result list + recursion stack
		public List<Integer> postorderTraversal(TreeNode root) {
			List<Integer> result = new ArrayList<>();
			traversal(root, result);
			return result;
		}
		public void traversal(TreeNode node, List<Integer> result){
			if(node == null) return;
			traversal(node.left, result);
			traversal(node.right, result);
			result.add(node.val);
		}
		
Approach 2 : Iterative approach(Using 2 stacks)
	public List<Integer> postorderTraversal(TreeNode root) {
        if(root == null) return new ArrayList<>();

        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack1 = new Stack<>(); // Stack1 to keep the track of childrens
        Stack<TreeNode> stack2 = new Stack<>(); // stack2 to build the soluction in reverse order
        stack1.push(root);

        while(!stack1.isEmpty()){
            TreeNode curr = stack1.pop();
            stack2.push(curr);
            if(curr.left != null) stack1.push(curr.left);
            if(curr.right != null) stack1.push(curr.right);
        }

        while(!stack2.isEmpty()){
            result.add(stack2.pop().val);
        }

        return result;
    }
	
	
Depth First Search(DFS) : DFS explores a tree by going as deep as possible along a branch before backtracking.
Breadth First Search(BFS) : BFS explores the tree level by level, explore all nodes at current level before moving deeper.

DFS => PreOrder, InOrder, PostOrder
BFS => LevelOrder, ZigZagOrder

DFS uses Stack
BFS uses Queue

4) Binary Tree Level order Traversal

Basic Code =>

Queue<Node> queue = new LinkedList<>();
List<Integer> result = new ArrayList<>();
queue.offer(root);

while(!queue.isEmpty()){
	Node curr = queue.poll();
	if(queue.left !=  null) queue.offer(curr.left);
	if(queue.right != null) queue.offer(curr.right);
	result.add(curr.val);
}
o/p: [1,2,3,4,5,6,7];

If we need ouput level wise [[1], [2,3], [4,5,6,7]] Go with below approach
Approach 1 : Iteractive approach 
	public List<List<Integer>> levelOrder(TreeNode root) {

        if(root == null) return new ArrayList<>();

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> levelList = null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            levelList = new ArrayList<>();
            int levelSize = queue.size();
            for(int i=0;i<levelSize;i++){ // Popping current level nodes and Pushing next level childrens
                TreeNode curr = queue.poll();
                if(curr.left != null) queue.offer(curr.left);
                if(curr.right != null) queue.offer(curr.right);
                levelList.add(curr.val);
            }
            result.add(levelList);
        }
      
      return result;
       
    }
Each tree node is:
    a) Enqueued exactly once
    b) Dequeued exactly once
✔️ Time Complexity = O(n)
	
Approach2 : Recursive approach ( Recursion always does the DFS )

public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> ans = new ArrayList<>();
    if (root == null) return ans;

    traversal(root, 0, ans);
    return ans;
}

private void traversal(TreeNode curr, int level, List<List<Integer>> ans) {
    if (curr == null) return;

    // If this level doesn't exist yet, create it
    if (ans.size() == level) {
        ans.add(new ArrayList<>());
    }

    ans.get(level).add(curr.val);

    traversal(curr.left, level + 1, ans);
    traversal(curr.right, level + 1, ans);
}

BFS approach → Uses Queue
DFS approach (this one) → Uses recursion + level index

5) Maximum Depth of Binary Tree
Approach 1 : TOP DOWN approach
class Solution {
    int maxDepth = 0;
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        traverse(root, 1);
        return maxDepth;
    }

    private void traverse(TreeNode curr, int depth){
        maxDepth = Math.max(maxDepth, depth);
        if(curr.left != null) traverse(curr.left, depth+1);
        if(curr.right != null) traverse(curr.right, depth+1);
    }

}

Approach 2 : BOTTOM UP Approach
	public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        int maxLeft = maxDepth(root.left);
        int maxRight = maxDepth(root.right);
        return Math.max(maxLeft, maxRight)+1;
    }
	
Time Complexity: O(n)
Space Complexity: O(h) Where h = height of the tree
Space Complexity for Skewed Tree in Worst Case: O(n)

6) Path Sum
Approach 1 : Top Down Approach
class Solution {
    boolean ans = false;
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        
        traverse(root, targetSum , 0);
        return ans;
    }

    private void traverse(TreeNode curr, int targetSum, int currSum){
        int newSum = currSum + curr.val;

        if(curr.left == null && curr.right == null){
            if(targetSum == newSum) ans = true;
        }

        if(curr.left != null) traverse(curr.left, targetSum, newSum);
        if(curr.right != null) traverse(curr.right, targetSum, newSum);
    }
}

Approach 2 : Bottom Up Approach
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;

        if(root.left == null && root.right == null){
            return root.val == targetSum;
        }
        int remainingSum = targetSum - root.val;
        boolean leftPathSum = hasPathSum(root.left, remainingSum);
        boolean rightPathSum = hasPathSum(root.right, remainingSum);
        return leftPathSum || rightPathSum;
    }

}

7) Count Complete Tree Nodes
Approach : Bottom Up Approach
class Solution {
    public int countNodes(TreeNode root) {
        if(root == null) return 0;

        int leftCount = countNodes(root.left);
        int rightCount = countNodes(root.right);
        return leftCount + rightCount + 1;
    }
}

8)Symmetric Tree
Approach 1 : Bottom Up Approach

A binary tree is symmetric if:
The left subtree is a mirror image of the right subtree
Mirror means: Left child ↔ Right child

Tree will be symmentri when
A.val == B.val && A.leftSubTree == B.rightSubTree && A.rightSubTree == B.leftSubTree

class Solution {
    public boolean isSymmetric(TreeNode root) {
       return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right){

		// If leaf node, return true
        if(left == null && right == null) return true;
		
		// One null, one not → structure mismatch → not symmetric
        if(left == null || right == null) return false;

        return (left.val == right.val) && 
            isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }
}
Approach 2 : Using Queue (BFS), This problem will be solved by traversing level  by level
class Solution {
    public boolean isSymmetric(TreeNode root) {
      Queue<TreeNode> queue = new LinkedList<>();
      //We push the first mirror pair:Left child & Right child
	  queue.offer(root.left);
      queue.offer(root.right);

      while(!queue.isEmpty()){
        TreeNode pair1 = queue.poll();
        TreeNode pair2 = queue.poll();
		
		//Both null → symmetric so far
        if(pair1 == null && pair2 == null) continue;

		//One null, one not → structure mismatch → not symmetric
        if(pair1 == null || pair2 == null) return false;
		
		// Values differ → not symmetric
        if(pair1.val != pair2.val) return false;

		//We enqueue:
		// Left of left ↔ Right of right
        queue.offer(pair1.left);
        queue.offer(pair2.right);
		// Right of left ↔ Left of right
        queue.offer(pair1.right);
        queue.offer(pair2.left);
      }

      return true;
    }
}

9) Invert Binary Tree
class Solution {
    public TreeNode invertTree(TreeNode root) {
        swapTree(root);
        return root;
    }

    public void swapTree(TreeNode node){
        if(node == null) return;

        TreeNode temp = node.left;
        node.left=node.right;
        node.right=temp;

        swapTree(node.left);
        swapTree(node.right);
    }
}

10) Same Tree
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        
        if(p == null && q == null) return true;
		
        //One null, one not → structure mismatch
        if(p == null || q == null) return false;

        if(p.val != q.val) return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
11) Balanced Binary Tree
class Solution {
    boolean ans = true;
    public boolean isBalanced(TreeNode root) {
        traverse(root);
        return ans;
    }

    public int traverse(TreeNode curr){

        if(curr == null){
            return 0;
        }
        int leftHeight = traverse(curr.left);
        int rightHeight = traverse(root.right);

        if(Math.abs(leftHeight- rightHeight) > 1){
            ans = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }
}

12) Diameter of Binary Tree
class Solution {
    int maxDiameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        findDepth(root);
        return maxDiameter;
    }

    public int findDepth(TreeNode curr){
        if(curr == null) return 0;

        int leftDepth = findDepth(curr.left);
        int rightDepth = findDepth(curr.right);

        int currentDiameter = leftDepth + rightDepth;
        maxDiameter = Math.max(maxDiameter, currentDiameter);
        return Math.max(leftDepth, rightDepth) +1;
    }
}

13) Binary Tree ZigZag Level Order Traversal
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        if(root == null) return new ArrayList<>();

        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean leftToRight = true;
        while(!queue.isEmpty()){
            int size = queue.size();
            Integer[] level = new Integer[size];
            for(int i=0;i<size;i++){
                TreeNode curr = queue.poll();
                int index = leftToRight ? i : size-1-i; // Instead of reversing a list after every level, We are finding the index and adding it
                level[index]=curr.val;

                if(curr.left != null) queue.offer(curr.left);
                if(curr.right != null) queue.offer(curr.right);
            }
            result.add(Arrays.asList(level));
            leftToRight = !leftToRight;
        }

        return result;
    }
}

14) SubTree of Another Tree
Approach 1:
class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(subRoot == null) return true;
        if(root == null) return false;

        if(root.val == subRoot.val && traverse(root, subRoot)){
            return true;
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public boolean traverse(TreeNode curr, TreeNode subRoot){

        if(curr == null && subRoot == null){
            return true;
        }
        if(curr == null || subRoot == null){
            return false;
        }
        if(curr.val != subRoot.val){
            return false;
        }
        return traverse(curr.left, subRoot.left) && traverse(curr.right, subRoot.right);        
    }
}
Time Complexity :O(N × M)
N = nodes in root
M = nodes in subRoot
Happens when many nodes match subRoot.val


Approach 2 : 
class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        String hashRoot = serialize(root); // Generating unique hash by using serilaization(Preorder serilaization)
        String hashSubRoot = serialize(subRoot);
        return hashRoot.contains(hashSubRoot);
    }

    public String serialize(TreeNode root){
         StringBuilder sb = new StringBuilder();
         serializeHelper(root, sb);
         return sb.toString();
    }

    public void serializeHelper(TreeNode curr, StringBuilder sb){
        if(curr == null){
            sb.append("-#"); // Adding Delimiter to disguish between nodes
            return;
        }
        sb.append("-").append(curr.val);
        serializeHelper(curr.left, sb);
        serializeHelper(curr.right, sb);
    }
}
TREE SERIALIZATION : 
Tree serialization means converting a tree structure into a linear format (string or array) so that:
The structure of the tree is preserved, the tree can be stored, transmitted, or compared.
It can later be reconstructed (deserialization)
    1
   / \
  2   3
     /
    4

Serialized (Preorder + null markers) : 1,2,#,#,3,4,#,#,# (delimiter : ",")
# represents null

🧠 Why null markers are IMPORTANT
Without nulls, structure is lost : 1,2,3,4
✅ With nulls : 1,2,#,#,3,4,#,#,# : 👉 Unique structure guaranteed

15) Lowest Common Ancestor of a Binary Tree
class Solution {

    TreeNode lca = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        traverse(root, p, q);
        return lca;
    }

    public int traverse(TreeNode curr, TreeNode p, TreeNode q){
        int count = 0;

        if(curr == null) return 0;
		
        if(curr.val == p.val || curr.val == q.val){
            ++count;
        }

        int leftCount = traverse(curr.left, p, q);
        int rightCount = traverse(curr.right, p, q);

        count = count + leftCount + rightCount;

        if(count == 2 && lca == null){
            lca = curr;
        }
        
        return count;
    }
}

16) Binary Tree Right Side View
Approach 1 : Iterative ( Level Order traversal from right to left, Push only the first element)
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null) return new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                TreeNode curr = queue.poll();
				// First node visited at this level is the rightmost one
                if(i == 0) ans.add(curr.val);
				
				// Push children to queue from right to left
                if(curr.right !=  null) queue.add(curr.right);
                if(curr.left !=  null) queue.add(curr.left);
               
            }
        }

        return ans;
    }
}

Approach 2 : Recursive
class Solution {
     List<Integer> ans = new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null) return new ArrayList<>();
        traverse(root, 0);
        return ans;
    }

    public void traverse(TreeNode curr, int level){
        if(curr == null) return;
		// First node visited at this level is the rightmost one
        if(ans.size() == level){
            ans.add(curr.val);
        }
		// Visit right first, then left
        traverse(curr.right, level+1);
        traverse(curr.left, level+1);
    }
}

17) Count Good Nodes in Binary Tree
Approach : Top Down Approach
class Solution {

    int count = 0;

    public int goodNodes(TreeNode root) {
        countGoodNodes(root.val, root);
        return count;
    }

    public void countGoodNodes(int maxSeenSoFar, TreeNode curr){

        if(curr.val >= maxSeenSoFar){
            maxSeenSoFar = curr.val;
            count++;
        }

        if(curr.left != null) countGoodNodes(maxSeenSoFar, curr.left);
        if(curr.right != null) countGoodNodes(maxSeenSoFar, curr.right);
    }
}

18) Populating Next Right Pointers in Each Node

class Solution {
    public Node connect(Node root) {
        if(root == null) return root;
        traverse(root);
        return root;
    }

    public void traverse(Node curr){

        if(curr.left !=  null){
            curr.left.next=curr.right;
        }

        if(curr.right != null && curr.next != null){
            curr.right.next = curr.next.left;
        }

        if(curr.left!= null) traverse(curr.left);
        if(curr.right != null) traverse(curr.right);
    }
}

🌳 Example Tree (Before)
        1
      /   \
     2     3
    / \   / \
   4   5 6   7
   
 Step 1 : curr.left.next = curr.right;   // 4.next = 5
        2
      /   \
     4 --> 5
step 2 : curr.right.next = curr.next.left; // 5.next = 3.left [crr.next(which is 3)]
     
	 2  -->   3
    / \  	 / \
   4   5 --> 6
✅ Final Tree with next Pointers
        1 -> null
      /   \
     2 --> 3 -> null
    / \   / \
   4 --> 5 --> 6 --> 7 -> null
   
19) Binary Tree Maximum Path Sum
Approach : Bottom Up
class Solution {
    int maxPathSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        traverse(root);
        return maxPathSum;
    }

    public int traverse(TreeNode curr) {
        if (curr == null) return 0;

        // Ignore negative paths
        int maxLeft = Math.max(0, traverse(curr.left));
        int maxRight = Math.max(0, traverse(curr.right));

        // Path passing through current node
        int currMax = curr.val + maxLeft + maxRight;
        maxPathSum = Math.max(maxPathSum, currMax);

        // Return best single path upward
        return curr.val + Math.max(maxLeft, maxRight);
    }
}

Input: [-10, 9, 20, null, null, 15, 7]

        -10
        /  \
       9    20
           /  \
         15    7

NODE 15
left = 0
right = 0
currMax = 15
maxPathSum = 15
return = 15

NODE 20
leftReturn  = 15
rightReturn = 7
currMax = 20 + 15 + 7 = 42
maxPathSum = 42
⬆️ Return only one side return = 20 + max(15, 7) = 35

		  -10
      [returns 25]
        /      \
	   9       20
	  [9]     [35]
              /   \
             15    7
            [15]  [7]

20) Construct Binary Tree from PreOrder and InOrder Traversal
class Solution {

    int preIndex = 0;
    Map<Integer, Integer> inorderIndexMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        // Build value -> index map for inorder
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return build(preorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int left, int right) {

        // Base case
        if (left > right) return null;

        // Pick root from preorder
        int rootVal = preorder[preIndex];
		preIndex++;
        TreeNode root = new TreeNode(rootVal);

        // Find root in inorder
        int inorderIndex = inorderIndexMap.get(rootVal);

        // Build left subtree
        root.left = build(preorder, left, inorderIndex - 1);

        // Build right subtree
        root.right = build(preorder, inorderIndex + 1, right);

        return root;
    }
}

Preorder = [3, 9, 20, 15, 7] : root,left,right
Inorder  = [9, 3, 15, 20, 7] : left,root,right

Preorder → first element is root
Inorder → left of root = left subtree, right of root = right subtree
preIndex moves forward only

🔁 Recursion Tree Summary
build(0,4) → root=3
├── build(0,0) → root=9
│   ├── null
│   └── null
└── build(2,4) → root=20
    ├── build(2,2) → root=15
    │   ├── null
    │   └── null
    └── build(4,4) → root=7
        ├── null
        └── null
✅ Final Constructed Tree
        3
       / \
      9   20
         /  \
       15    7
	   
21) Flatten Binary Tree to Linked List

Preorder = root → left → right
But if we traverse normal preorder, we lose the right subtree.

👉 So we do REVERSE preorder: right → left → root
This lets us build the list backwards using a prev pointer.

class Solution {

    TreeNode prev = null;

    public void flatten(TreeNode root) {
        if (root == null) return;

        // Reverse preorder: Right → Left → Root
        flatten(root.right);
        flatten(root.left);

        root.right = prev;
        root.left = null;
        prev = root;
    }
}

Input Binar Tree =
    1
   / \
  2   5
 / \   \
3   4   6

Output(Flatten Linked List) = 1 → 2 → 3 → 4 → 5 → 6

				BINARY SEARCH TREE(BST)
1) BST rules (very important)
For every node:
		a) All values in the left subtree must be less than the node
		b) All values in the right subtree must be greater than the node
		c) This rule must hold for the entire subtree, not just immediate children
2) Inorder Traversal of BST is always sorted


22) Validate Binary Search Tree
class Solution {
    public boolean isValidBST(TreeNode root) {
        return isBST(root, Long.MIN_VALUE, Long.MAX_VALUE);   
    }

    public boolean isBST(TreeNode curr, long low, long high){

        if(curr == null) return true;

        if(curr.val <= low || curr.val >= high){
            return false;
        }
	
        boolean isleftBST = isBST(curr.left, low, curr.val);
        boolean isRightBST = isBST(curr.right, curr.val, high);

        return isleftBST && isRightBST;
    }
}

Time Complexity = O(n) as we have visit each & every node
Space Complexity = O(n)

Valid BST
      10
     /  \
    5    15
        /  \
       12  20
Call stack:
10 → (-∞, ∞)
5  → (-∞, 10)
15 → (10, ∞)
12 → (10, 15)
20 → (15, ∞)
✅ All values stay within their ranges
//For the left child:
	Minimum stays the same (low)
	Maximum becomes the current node’s value (curr.val)
➡️ Left subtree must contain only smaller values
//For the right child:
	Minimum becomes curr.val
	Maximum stays the same (high)
➡️ Right subtree must contain only larger values

23) Search in a Binary Search Tree
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        
        if(root == null) return null;
        
        if(root.val == val) return root;

        if(val < root.val){
            return searchBST(root.left, val);
        }else{
            return searchBST(root.right, val);
        }
    }
}
Time Complexity = O(log n)

24) Insert into a Binary Search tree
Approach : Bottom Up Approach
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        return traverse(root, val);
    }

    public TreeNode traverse(TreeNode curr, int val){

        if(curr == null){
            return new TreeNode(val);
        }

        if(val < curr.val){
            curr.left = traverse(curr.left, val);
        }else{
            curr.right = traverse(curr.right, val);
        }

        return curr;
    }
}

25) Kth Smallest element in a BST
Approach : Inorder Traversal
class Solution {
    int ans = -1;
    int count = 0;
    public int kthSmallest(TreeNode root, int k) {
        inOrderTraverse(root, k);
        return ans;
    }

    public void inOrderTraverse(TreeNode curr, int k){
        
       
        if(curr == null) return;

        inOrderTraverse(curr.left, k);
        
		count++;
        if(count == k){
            ans = curr.val;
            return;
        }

        inOrderTraverse(curr.right, k);
       
    }
}

26)Lowest Common Ancestor of a Binary Search Tree
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return traverse(root, p, q);
    }

    public TreeNode traverse(TreeNode curr, TreeNode p, TreeNode q){
        if(p.val < curr.val && q.val < curr.val){ // If bothvalues are less than current node , Go to Left subtree
            return traverse(curr.left, p, q);
        }
        else if(p.val > curr.val && q.val > curr.val){ // If both values are greater than current node , Go to Right subtree
            return traverse(curr.right, p, q);
        }else{
            return curr;
        }
    }
}
Time complexity : O(log n)