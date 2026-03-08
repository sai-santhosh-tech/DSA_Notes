Graphs :
A graph is a data structure that helps us represent relationships or connection between objects.


✅ Graph Representation (Adjacency List)
import java.util.*;

class Graph {
    int V;  // no of vertices (nodes)
    List<List<Integer>> adj;

    Graph(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>()); // For each Node, creating list of adjacents
        }
    }

    void addEdge(int u, int v) {
        adj.get(u).add(v);

        // For undirected graph, also add reverse edge
        // adj.get(v).add(u);
    }
	
	List<Integer> getAdjacents(int v){
		return adj.get(v);
	}
}

Graph g = new Graph(5);

g.addEdge(0, 1);
g.addEdge(0, 2);
g.addEdge(1, 3);
g.addEdge(2, 4);

📌 Adjacency List Representation

It will look like this internally:

0 → [1, 2]
1 → [3]
2 → [4]
3 → []
4 → []

📌 Visual Graph Structure
🌳 Tree-like Structure
        0
       / \
      1   2
     /     \
    3       4
	

🚀 BFS (Breadth First Search)

public void bfs(int start){
	Queue<Integer> queue = new LinkedList<>();
	Set<Integer> visited = new HashSet<>();   // Visited Set contains output in BFS
	queue.offer(start);
	visited.add(start);
	
	while(!queue.isEmpty()){
		int curr = queue.poll();
		
		for(int adjacent : adj.get(curr)){
			if(!visted.contains(adjacent)){
				queue.offer(adjacent);
				visited.add(adjacent);
			}
		}
	}	
}
🔹 When to use?
Shortest path in unweighted graph
Level order traversal
Multi-source problems

🔹 Time Complexity: O(V + E)


🚀 DFS (Iterative using Stack)

public void dfsterative(int start){
	Stack<Integer> stack = new Stack<>();
	Set<Integer> visited = new HashSet<>(); // Visited Set contains output in DFS
	stack.push(start);
	
	while(!stack.isEmpty()){
		int curr = stack.pop();
		
		if(!visited.contans(curr)){
			visited.add(curr);
			
				for(int adjacent : adj.get(curr)){
					if(!visited.contains(adjacent)){
						stack.push(adjacent);
					}
				}
		}
	}
	
}

DFS (Recursive)

void dfs(int start) {
    Set<Integer> visited = new HashSet<>();
    dfsHelper(start, visited);
}

public void dfsHelper(int curr, Set<Integer> visited){
	visited.add(curr);
	
	for(int adjacent : adj.get(curr)){
		if(!visited.contans(adjacent)){
			dfsHelper(adjacent, visited);
		}
	}
}

DFS
When to use?
	Detect cycle
	Topological sort
	Backtracking
	Connected components

🔹 Time Complexity: O(V + E)


1) Clone Graph
 Approach 1 : BFS using Queue
 class Solution {
    public Node cloneGraph(Node node) {
        if(node == null) return null;

        Queue<Node> queue = new LinkedList<>();
        Map<Node, Node> visited = new HashMap<>(); // Contains actual Node and Cloned Node
        Node cloneRoot = new Node(node.val);
        queue.offer(node);     // In queue will add the actual Node not cloned
        visited.put(node, cloneRoot);

        while(!queue.isEmpty()){
            Node curr = queue.poll();        // actual Node
            Node cloneCurr = visited.get(curr);  // Getting ClonedNode from map using actualNode

            for(Node neighbor : curr.neighbors){  // Traversing actual Node adjacents and making clone adjacents from it.
                if(!visited.containsKey(neighbor)){
                    queue.offer(neighbor);        // In queue will add the actual Node not cloned
                    visited.put(neighbor, new Node(neighbor.val));
                }
                cloneCurr.neighbors.add(visited.get(neighbor)); // Adding neighbors to clonedNodes
            }
        }

        return cloneRoot;
    }
}

Approach 2 : DFS using Stack [ Just replace Queue with Stack]

class Solution {
    public Node cloneGraph(Node node) {
        if(node == null) return null;

        Stack<Node> stack = new Stack<>();
        Map<Node, Node> visited = new HashMap<>();
        Node cloneRoot = new Node(node.val);
        stack.push(node);
        visited.put(node, cloneRoot);

        while(!stack.isEmpty()){
            Node curr = stack.pop();
            Node cloneCurr = visited.get(curr);

            for(Node neighbor : curr.neighbors){
                if(!visited.containsKey(neighbor)){
                    stack.push(neighbor);
                    visited.put(neighbor, new Node(neighbor.val));
                }
                cloneCurr.neighbors.add(visited.get(neighbor));
            }
        }

        return cloneRoot;
    }
}


2)  Find if Path Exists in Graph

Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
Output: true
Explanation: There are two paths from vertex 0 to vertex 2:
- 0 → 1 → 2
- 0 → 2

Visual Representation (Graph Diagram)
      1
     / \
    /   \
   0-----2
   
Adjacency List Representation
0 → [1, 2]
1 → [0, 2]
2 → [1, 0]

As Java structure:
adj.get(0) = [1, 2]
adj.get(1) = [0, 2]
adj.get(2) = [1, 0]


Approach 1 : BFS
class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
		
		 // Step 1: Create empty adjacency list
        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>()); // For each Node/vertex creating 1 adjacent list
        }
		
		// Step 2: Fill adjacency list
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            adj.get(u).add(v);  // u -> v
            adj.get(v).add(u);	// v -> u (because undirected graph)
        }
		
		// step 3 : Traversing the Graph
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(source);
        visited.add(source);

        while(!queue.isEmpty()){
            int curr = queue.poll();

            if(curr == destination) return true;

                for(int adjacent : adj.get(curr)){
                    if(!visited.contains(adjacent)){
                        queue.offer(adjacent);
                        visited.add(adjacent);
                    }
                }
        }

        return false;
    }
 }
}

Time and Space Complexity => O(n + E)
Where, n => no of vertices(nodes)
	   E => no of edges
	   
Approach 2 : DFS using Stack(Just replace Queue with Stack)

Approach 3 : DFS using Recusion
class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {

        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            adj.get(u).add(v);
            adj.get(v).add(u);
        }
    
        Set<Integer> visited = new HashSet<>();
        return dfs(source, destination, adj, visited);
    }

    public boolean dfs(int current, int destination,  List<List<Integer>> adj, Set<Integer> visited){

        if(current == destination) return true;

        visited.add(current);
        
        for(int adjacent : adj.get(current)){
            if(!visited.contains(adjacent)){
                if(dfs(adjacent, destination, adj, visited)){
                    return true;
                }
            }
        }

        return false;
    }
}


3) All Paths From Source to Target

Here we are trying to explore all paths, so use backtrack concept

class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int start = 0;
        int end = graph.length-1; 
        List<List<Integer>> allPaths =new ArrayList<>();
        dfs(graph, start, end, new ArrayList<>(), allPaths);
        return allPaths;
    }

    public void dfs(int[][] graph, int curr, int end, List<Integer> currentPath,  List<List<Integer>> allPaths){

        currentPath.add(curr);   // adding current element

        if(curr == end){
            allPaths.add(new ArrayList<>(currentPath));
        }else{
             for(int neighbor : graph[curr]){
                dfs(graph, neighbor, end, currentPath, allPaths); // exploring neighbors
            }
        }

        currentPath.remove(currentPath.size()-1); // removing last element to backtrack
       
    }
}

4) Cycle Detection in UNDIRECTED GRAPH

If a visited neighbor is NOT parent → cycle

        0
        |
        1
       / \
      2   4
       \ /
        3
Edges (undirected):
0 - 1
1 - 2
2 - 3
3 - 4
4 - 1

This forms a cycle:
1 - 2 - 3 - 4 - 1

During DFS:
If we see a visited neighbor that is NOT the parent → cycle exists.

We start DFS from node 0.

▶ Step 1: Visit 0
visited = {0}
Parent of 0 = -1
Move to neighbor 1

▶ Step 2: Visit 1
visited = {0,1}
Parent of 1 = 0

Neighbors of 1:
0, 2, 4
0 is visited
Is 0 parent of 1? YES → ignore

▶ Step 5: Visit 4
visited = {0,1,2,3,4}
Parent of 4 = 3
Neighbors:
3, 1
3 is visited
Is 3 parent? YES → ignore

Next neighbor → 1
1 is visited
Is 1 parent of 4? ❌ NO
🚨 That means:
4 - 1
This edge connects to a visited node that is NOT parent.💥 Cycle Detected

i) Undirected Connected Graph
	👉 Every node is reachable from some starting node
	👉 Single component

🔹 Example (Connected + Cycle)
      0--1---2
         |   |
         |   |
         4 ← 3
		 
public class DetectCycleInUnDirectedGraph {

	public static void main(String[] args) {
		
		dfs(new int[][]{ {0,1}, {1,2}, {2,0} });  // true
		
		dfs(new int[][]{ {0,1}, {1,2}, {2,3} });  // false
		
		dfs(new int[][]{ {0,1}, {1,2}, {2,3}, {3,4}, {4,0}});  // true
		
	}
	
	public static void dfs(int[][] graph) {
		Set<Integer> visited = new HashSet<>();
		
		List<List<Integer>> adj = new ArrayList<>();
		
		int n = 0;
		 // Find number of vertices
		for(int[] vertex : graph) {
			n = Math.max(n, Math.max(vertex[0], vertex[1]));
		}
		n++;
		  // Initialize adjacency list		
		for(int i=0;i<n;i++) {
			adj.add(new ArrayList<>());
		}
		 // Build undirected graph
		for(int[] edges : graph) {
			int x = edges[0];
			int y = edges[1];
			
			adj.get(x).add(y);
			adj.get(y).add(x);
		}
		
		System.out.println(dfsHelper(adj, 0, -1, visited));
	}
	
	public static boolean dfsHelper(List<List<Integer>> adj, int curr, int parent, Set<Integer> visited) {
		
		visited.add(curr);
		
		for(int neighbor : adj.get(curr)) {
			if(!visited.contains(neighbor)) {
				 if(dfsHelper(adj, neighbor, curr, visited)) {
					 return true;
				 }
			}else if(neighbor != parent) {
				return true; // Cycle detected
			}
		}
		
		return false;
	}

}

i) Undirected DisConnected Graph
👉 Graph has multiple components
👉 DFS must start from each unvisited node

🔹 Example (Disconnected, One Component Has Cycle)
Component 1:        Component 2:

      0-1---2          4 → 5
        |   |
        |   |
        -───3
🔍 Explanation
Component 1 has a cycle
Component 2 is acyclic
Overall graph has a cycle
Must run DFS from every node

public class DetectCycleInUnDirectedGraph {

	public static void main(String[] args) {
		
		dfs(new int[][]{ {0,1}, {1,2}, {2,0} });
		
		dfs(new int[][]{ {0,1}, {1,2}, {2,3} });
		
		dfs(new int[][]{ {0,1}, {1,2}, {2,3}, {3,4}, {4,0}});
		
	}
	
	public static void dfs(int[][] graph) {
		Set<Integer> visited = new HashSet<>();
		
		List<List<Integer>> adj = new ArrayList<>();
		
		int n = 0;
		 // Find number of vertices
		for(int[] vertex : graph) {
			n = Math.max(n, Math.max(vertex[0], vertex[1]));
		}
		n++;
		  // Initialize adjacency list		
		for(int i=0;i<n;i++) {
			adj.add(new ArrayList<>());
		}
		 // Build undirected graph
		for(int[] edges : graph) {
			int x = edges[0];
			int y = edges[1];
			
			adj.get(x).add(y);
			adj.get(y).add(x);
		}
		
		 // Check all components
		for(int i=0;i<n;i++) {
			if(!visited.contains(i)) {
				if(dfsHelper(adj, i, -1, visited)) {
					System.out.println(true);
					return;
				}
			}
		}
		
		System.out.println(false);
		
	}
	
	public static boolean dfsHelper(List<List<Integer>> adj, int curr, int parent, Set<Integer> visited) {
		
		visited.add(curr);
		
		for(int neighbor : adj.get(curr)) {
			if(!visited.contains(neighbor)) {
				 if(dfsHelper(adj, neighbor, curr, visited)) {
					 return true;
				 }
			}else if(neighbor != parent) {
				return true; // Cycle detected
			}
		}
		
		return false;
	}

}

5) Cycle Detection in DIRECTED GRAPH

✅ Example Directed Graph (With Cycle)
    0 → 1 → 2
         ↑   |
         |   ↓
         4 ← 3
		 
Edges:
0 → 1
1 → 2
2 → 3
3 → 4
4 → 1

Cycle:
1 → 2 → 3 → 4 → 1

What We Track in Directed Graph
We use:
visited[]      → node has been visited before
recStack[]     → node is currently in DFS path

Cycle condition:
If neighbor is already in recStack → cycle

▶ Step 1: Start from node 0
Path: 0
visited   = [T, F, F, F, F]
recStack  = [T, F, F, F, F]

▶ Step 2: Move to 1
Path: 0 → 1
visited   = [T, T, F, F, F]
recStack  = [T, T, F, F, F]

▶ Step 5: Move to 4
Path: 0 → 1 → 2 → 3 → 4
visited   = [T, T, T, T, T]
recStack  = [T, T, T, T, T]

🚨 Step 6: From 4 → 1
Now check neighbor 1.
Is 1 visited? → YES
Is 1 in recursion stack? → YES ✅ 💥 Cycle Detected

public class DetectCycleInDirectedGraph {

	public static void dfs(int[][] graph) {
		Set<Integer> visited = new HashSet<>();
		
		List<List<Integer>> adj = new ArrayList<>();
		
		int n = 0;
		 // Find number of vertices
		for(int[] vertex : graph) {
			n = Math.max(n, Math.max(vertex[0], vertex[1]));
		}
		n++;
		  // Initialize adjacency list		
		for(int i=0;i<n;i++) {
			adj.add(new ArrayList<>());
		}
		  // Build directed graph
		for(int[] edges : graph) {
			int x = edges[0];
			int y = edges[1];
			adj.get(x).add(y);
		}
		
		boolean[] recursionStack = new boolean[n];
		
		 // Check all components
		for(int i=0;i<n;i++) {
			if(!visited.contains(i)) {
				if(dfsHelper(adj, i, visited, recursionStack)) {
					System.out.println(true);
					return;
				}
			}
		}
		
		System.out.println(false);
		
	}
	
	public static boolean dfsHelper(List<List<Integer>> adj, int curr, Set<Integer> visited, boolean[] recursionStack) {
		
		visited.add(curr);
		recursionStack[curr] = true;
		
		for(int neighbor : adj.get(curr)) {
			if(!visited.contains(neighbor)) {
				 if(dfsHelper(adj, neighbor, curr, visited)) {
					 return true;
				 }
			}else if(recursionStack[neighbor]) {
				return true; // Cycle detected
			}
		}
		
		recursionStack[curr] = false;  // Backtrack
		
		return false;
	}

}

In directed graph:
	If neighbor is visited AND neighbor is still in recursion stack
👉 That means a back edge exists
👉 Cycle exists


⏱ Complexity
Time: O(V + E)
Space: O(V)

TOPOLOGICAL SORT
It is a linear ordering of nodes of a DAG(Directed Acyclic Graph) such that for every directed edge (u -> v), node u comes before v.

Example
An adjacency list represents a graph.


        const adj = [
            [],          // 0
            [],          // 1
            [3],         // 2 -> 3
            [1],         // 3 -> 1
            [0, 1],      // 4 -> 0, 1
            [0, 2]       // 5 -> 0, 2 
        ]
      
Representation
        5 ─────▶ 0 ◀───── 4
        │                 │
        ▼                 ▼
        2 ─────▶ 3 ─────▶ 1

After Topological sort => 5, 0, 2, 3, 1, 4
5 ─────▶ 0 => 5 should come before 0
5 ─────▶ 2 => 5 should come before 2

6) Topological Sort using DFS

public class TopologialSortDFS {

	public static void main(String[] args) {
		int[][] adj= {{}, {}, {3}, {1}, {0,1}, {0,2}};
		int n = adj.length;
		boolean[] visited = new boolean[n];
		List<Integer> result = new ArrayList<>();
		
		for(int i=0; i < n;i++) {
			if(!visited[i]) {
				dfs(i, adj, result, visited);
			}
		}
		
		Collections.reverse(result);
		System.out.println(result);
	}

	public static void dfs(int curr, int[][] adj, List<Integer> result, boolean[] visited) {
		visited[curr]=true;
		
		for(int neighbor : adj[curr]) {
			if(!visited[neighbor]) {
				dfs(neighbor, adj, result, visited);
			}
		}
		
		result.add(curr);
	}
}


7) Topological sort using BFS (Kahn’s Algorithm)

public class TopologicalSortKahnsAlgorithm {

	public static void main(String[] args) {
		int[][] adj= {{}, {}, {3}, {1}, {0,1}, {0,2}};
		int n = adj.length;
		List<Integer> result = new ArrayList<>();
		int[] indegree = new int[n];
		Queue<Integer> queue = new LinkedList<>();
		
	    // Step 1: Calculate indegree
		for(int i=0;i<n;i++) {
			for(int node : adj[i]) {
				indegree[node]=indegree[node]+1;
			}
		}
		//System.out.println(Arrays.toString(indegree));
		
		// Step 2: Add nodes with indegree 0
		for(int i=0;i<n;i++) {
			if(indegree[i] == 0) queue.offer(i);
		}
		
		 // Step 3: BFS
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			result.add(curr);
			
			for(int neighbor: adj[curr]) {
				indegree[neighbor]--;
				
				if(indegree[neighbor] == 0) queue.offer(neighbor);
			}
		}
		
		System.out.println(result);
	}

}

Shortest Path Algorithms
1) BFS (Unweighted Graph): It is used to calculate the distance between source and destination in Unweighted (no weights on edges) graph.
2) Dijkstra’s (Weighted): It is used to calculates distance in Weighted Graph. It uses Priority Queue and Greedy Algorithm.
Drawback: It doesn't work on the negative weight edges.
3) Bellman–Ford Algorithm (Weighted + Negative): It calculates distance for the Weighted and Negative Edges as well.
Note: These above three algorithms are the 'Single Source Shortest Path' Algorithm.
4) Floyd–Warshall (All pairs shortest path): It calculates all pairs of shortest path algorithm.


8) SHORTEST DISTANCE USING BFS

Shortest Distance in Directed Unweighted Graph => 

public class ShortestDistanceInUnweightedGraph {

	public static void main(String[] args) {
		int[][] adj = { {1,2}, {3}, {4}, {5}, {3}, {} };
		int n = adj.length;
		int[] distance = new int[n];
		Arrays.fill(distance, Integer.MAX_VALUE);
		Queue<Integer> queue =new LinkedList<>();
		int src = 0;
		distance[src]=0;
		shortestDistance(src, adj, distance, queue);
		System.out.println(Arrays.toString(distance));
		
	}
	
	public static void shortestDistance(int src, int[][] adj, int[] distance, Queue<Integer> queue) {
		queue.offer(src);
		
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			
			for(int neighbor : adj[curr]) {
				if(distance[neighbor] == Integer.MAX_VALUE) {
					distance[neighbor] = distance[curr] +1;
					queue.offer(neighbor);
				}
			}
		}
	}

}

Shortest Distance in Undirected Unweighted Graph => 

public class ShortestDistanceInUnweightedGraph {

    public static void main(String[] args) {
        int[][] adj = {
            {1,2},        // 0
            {0,3},        // 1
            {0,4},        // 2
            {1,5,4},      // 3
            {2,3},        // 4
            {3}           // 5
        };

        int n = adj.length;
        int[] distance = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(distance, Integer.MAX_VALUE);

        int src = 0;
        bfs(src, adj, distance, visited);

        System.out.println(Arrays.toString(distance));
    }

    public static void bfs(int src, int[][] adj, int[] distance, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(src);
        visited[src] = true;
        distance[src] = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int neighbor : adj[curr]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    distance[neighbor] = distance[curr] + 1;
                    queue.offer(neighbor);
                }
            }
        }
    }
}
