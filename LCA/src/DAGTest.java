import static org.junit.Assert.*;
import org.junit.Test;

public class DAGTest {

	@Test
	public void testAddEdge(){
		DAG LCA = new DAG(5);
		LCA.addEdge(0, 1);
		LCA.addEdge(-2, -5);
		
		assertEquals(1, LCA.E()); //if Edge count is 1
		
		LCA.addEdge(1, 2);
		
		assertEquals(2, LCA.E()); //Two edges
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

}