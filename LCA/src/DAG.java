import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class DAG {
	private int V; // Vertices
	private int E; // Edges
	private ArrayList<Integer>[] adj; // adj[V] = adjacency list for vertex V
	private int[] indegree; // indegree of vertices or edges
	private int[] outdegree; // outdegree of vertices or edges
	private boolean marked[]; // if a vertices has been used
	private boolean hasCycle; // true or false for cycle
	private boolean stack[];

	public DAG(int V) // Implementation of graph with size v
	{
		if (V < 0) {
			throw new IllegalArgumentException("Number of vertices must be greater than 0");
		}

		this.V = V;
		this.E = 0;
		indegree = new int[V];
		marked = new boolean[V];
		stack = new boolean[V];
		adj = (ArrayList<Integer>[]) new ArrayList[V];

		for (int v = 0; v < V; v++) {
			adj[v] = new ArrayList<Integer>();
		}
	}

	public boolean hasCycle() {
		return hasCycle;
	}

	// Returns current vertex
	public int V()

	{

		return V;

	}

	public int E()

	{

		return E;

	}

	// Returns the adjacent vertices to v
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}

	private int validateVertex(int v) {
		if (v < 0 || v >= V) {
			return -1;
		} else {
			return 1;
		}
	}

	// Returns number of directed edges to vertex v
	public int indegree(int v) {
		if (validateVertex(v) > 0) {
			return indegree[v];
		} else {
			return -1;
		}

	}

	// Returns number of directed edges from vertex v
	public int outdegree(int v) {
		if (validateVertex(v) > 0) {
			return adj[v].size();
		} else {
			return -1;
		}
	}

	public void findCycle(int v) {
		marked[v] = true;
		stack[v] = true;

		for (int w : adj(v)) {
			if (!marked[w]) {
				findCycle(w);
			} else if (stack[w]) {
				hasCycle = true;
				return;
			}
		}
		stack[v] = false;
	}

	// implementing LCA
	public int findLCA(int v, int w) {
		findCycle(0);

		if (hasCycle) // if the graph has a cycle it is not DAG
		{
			return -1;
		} else if (validateVertex(v) < 0 || validateVertex(w) < 0) {
			// Valid vertices are non-negative
			return -1;
		} else if (E == 0) {
			// Graph is empty
			return -1;
		}

		DAG reverse = reverse();

		ArrayList<Integer> array1 = reverse.BFS(v);
		ArrayList<Integer> array2 = reverse.BFS(w);
		ArrayList<Integer> commonAncestors = new ArrayList<Integer>();

		boolean found = false;

		for (int i = 0; i < array1.size(); i++) {
			for (int j = 0; j < array2.size(); j++) {
				if (array1.get(i) == array2.get(j)) {
					commonAncestors.add(array1.get(i));
					found = true;
				}
			}
		}

		if (found) {
			// Return first element in list - LCA
			return commonAncestors.get(0);
		} else {
			return -1; // Nothing found
		}
	}

	// Prints BFS(Breadth-First search) from source s
	public ArrayList<Integer> BFS(int s) {
		ArrayList<Integer> order = new ArrayList<Integer>();
		boolean visited[] = new boolean[V]; // Marks vertices as not visit
		LinkedList<Integer> queue = new LinkedList<Integer>();

		visited[s] = true;
		queue.add(s);

		while (queue.size() != 0) {
			s = queue.poll(); // Sets s to the head of the list
			order.add(s);

			// Find adjacent vertices to s. If not visited,
			// mark as visited (true) and enqueue
			Iterator<Integer> i = adj[s].listIterator();

			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					visited[n] = true;
					queue.add(n);
				}
			}
		}
		return order;
	}

	public void addEdge(int v, int w) {
		if ((validateVertex(v) > 0) && (validateVertex(w) > 0)) {
			adj[v].add(w);
			indegree[w]++;
			E++;
		} else {
			System.out.println("Enter numbers from 0 - " + (V - 1));
		}
	}

	// Reverse DAG
	public DAG reverse() {
		DAG reverse = new DAG(V);
		for (int v = 0; v < V; v++) {
			for (int w : adj(v)) {
				reverse.addEdge(w, v);
			}
		}
		return reverse;
	}

}
