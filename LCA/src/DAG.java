import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;



public class DAG {
	private int V;			         	// Vertices		
	private int E;					    // Edges
	private ArrayList<Integer>[] adj;    //adj[V] = adjacency list for vertex V
	private int [] indegree;			    // indegree of vertices or edges
	private int [] outdegree;			// outdegree of vertices or edges
	private boolean marked [];			// if a vertices has been used
	private boolean hasCycle;			// true or false for cycle
	private boolean stack [];			
	
	
	public DAG(int V) //Implementation of graph with size v
	{
		if(V < 0)
		{
			throw new IllegalArgumentException("Number of vertices must be greater than 0");
		}
		n
		this.V = V;
		this.E = 0;
		indegree = new int[V];
		marked = new boolean[V];
		stack = new boolean[V];
		adj = (ArrayList<Integer>[]) new ArrayList[V];
		
		for(int v = 0; v < V; v++)
		{
			adj[v] = new ArrayList<Integer>();
		}
	}

}
