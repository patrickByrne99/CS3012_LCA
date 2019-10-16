import static org.junit.Assert.*;
import org.junit.Test;

public class DAGTest {

	@Test
	public void testDAG(){
		DAG test = new DAG(10);
		test.addEdge(1, 2);
		test.addEdge(1, 3);
		test.addEdge(3, 4);
		test.addEdge(4, 5);
		test.addEdge(4, 6);
		
		assertEquals(1, test.indegree(4));
		assertEquals(2, test.outdegree(4));
		assertEquals(5, test.E());
		assertEquals(10, test.V());
		String ans = "[5, 6]";
		assertEquals(ans, test.adj(4).toString());
	}
	@Test 
	public void testV()
	{
		DAG graph = new DAG(6);
		assertEquals(6, graph.V());
	}
	
	@Test
	public void testE(){
		
		DAG graph = new DAG(5);
		
		graph.addEdge(1, 2);
		graph.addEdge(2, 4);
		graph.addEdge(3, 3);
		
		assertEquals(3, graph.E());
	}
	@Test
	public void testAddEdge(){
		DAG LCA = new DAG(5);
		LCA.addEdge(0, 1);
		LCA.addEdge(-2, -5);
		
		assertEquals(1, LCA.E()); //if Edge count is 1
		
		LCA.addEdge(1, 2);
		
		assertEquals(2, LCA.E()); //Two edges
	}

	@Test(expected=Exception.class)
	public void exceptionTest(){
		
		//Can't make a directed graph with less than 0 vertices
		
		DAG LCA = new DAG(-5);
	}
	
	// each new DAG called LCA in each test is different
	@Test
	public void testLCAEmpty() { 
		DAG LCA = new DAG(10);
		assertEquals(-1, LCA.findLCA(1, 2)); 
	}
	
	@Test
	public void testinDegree(){
		DAG LCA = new DAG(5);
		assertEquals(-1, LCA.indegree(-3));
	}
	
	@Test
	public void testOutDegree(){
		DAG LCA = new DAG(5);
		assertEquals(-1, LCA.outdegree(8));	
	}
	@Test
	public void testAdj()
	{
		DAG graph = new DAG(5);
		
		graph.addEdge(1, 2);
		graph.addEdge(2, 4);
		graph.addEdge(3, 3);
		graph.addEdge(4, 3);
		
		String adj = "[4]";
		assertEquals(adj, graph.adj(2).toString());
	}
	
	@Test
	public void testforCycle()
	{
		DAG graph = new DAG(10);
		
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 0);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		
		graph.findCycle(0);
		
		assertTrue(graph.hasCycle());
	}
	@Test
	public void testAcyclicGraph()
	{
		DAG graph = new DAG(10);
		
		graph.addEdge(1, 2);
		graph.addEdge(2, 4);
		graph.addEdge(3, 3);
		
		graph.findCycle(1);
		assertFalse(graph.hasCycle());
	}
	
	@Test
	public void testLCA(){
		DAG LCA = new DAG(10);
		
		LCA.addEdge(0, 1);
		LCA.addEdge(1, 2);
		LCA.addEdge(1, 3);
		LCA.addEdge(2, 5);
		LCA.addEdge(3, 4);
		LCA.addEdge(4, 6);
		LCA.addEdge(5, 6);
		LCA.addEdge(6, 8);
		LCA.addEdge(5, 7);
		LCA.addEdge(7, 8);
		LCA.addEdge(8, 9);
		
		assertEquals(1, LCA.findLCA(5, 4)); //4 and 5
		assertEquals(7, LCA.findLCA(8, 7)); //7 and 8
		assertEquals(6, LCA.findLCA(6, 8)); //6 and 8
		assertEquals(2, LCA.findLCA(2,2)); //where both parameters are same verteX
	}
	
	@Test
	public void testLCAForNoCommonAncestors(){
		DAG LCA = new DAG(11);
		
		LCA.addEdge(0, 1);
		LCA.addEdge(0, 2);
		LCA.addEdge(1, 2);
		LCA.addEdge(2, 3);
		LCA.addEdge(3, 4);
		LCA.addEdge(1, 5);
		LCA.addEdge(3, 5);
		
		assertEquals(0, LCA.findLCA(3, 1));  //no LCA
		assertEquals(2, LCA.findLCA(3, 2));
		assertEquals(3, LCA.findLCA(4, 5));
		
		//Check for no common ancestors
		assertEquals(-1, LCA.findLCA(7, 3)); //one node doesn't exist
	}
	
	@Test
	public void testLCAForEmptyDAG()
	{
		DAG graph = new DAG(5);
		assertEquals(-1, graph.findLCA(0, 2));
		assertEquals(-1, graph.findLCA(0, 4));
		assertEquals(-1, graph.findLCA(0, 0));
	}
	
	@Test
	public void testLCAForNonDAG()
	{
		DAG graph = new DAG(10);
		
		graph.addEdge(0, 1);
		graph.addEdge(0, 3);
		graph.addEdge(3, 2);
		graph.addEdge(2, 0);
		graph.addEdge(2, 4);
		
		assertEquals(-1, graph.findLCA(3, 2));
		assertEquals(-1, graph.findLCA(2, 4));
		assertEquals(-1, graph.findLCA(1, 3));
		assertEquals(-1, graph.findLCA(0, 3));
		assertEquals(-1, graph.findLCA(1, 2));
		
	}

}