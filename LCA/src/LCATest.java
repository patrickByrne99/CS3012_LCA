import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LCATest {

	@Test
	public void testIsEmpty() 
 	{
		LCA<Integer, Integer> test = new LCA<Integer, Integer>();
		boolean isEmpty = test.isEmpty();
		assertEquals(isEmpty, true);//new tree is empty, nothing added
		test.put(1, 2);
		test.put(2, 3);
		test.put(3, 4);
		test.put(4, 5);
		isEmpty = test.isEmpty();
		assertEquals(isEmpty, false);// not empty, as nodes were added

 	}
	
	@Test
	public void testSize(){
		LCA<Integer, Integer> test = new LCA<Integer, Integer>();
		assertEquals(test.size(), 0);//new tree
		test.put(1, 3);
		assertEquals(test.size(), 1);//First element added
		test.put(2, 4);
		test.put(3, 5);
		test.put(4, 6);
		assertEquals(test.size(), 4);//Two more element added
		test.put(2, 8);
		assertEquals(test.size(), 4); //Size remains same as element already exists
	}
	@Test
	public void testOneNode()
	{
		LCA<Integer, Integer> test = new LCA<Integer, Integer>();
		test.put(4, 1);
		assertNull(test.search(test.root, 4, 3)); //Testing for one of the nodes not existing
		assertNull(test.search(test.root, 2, 8)); //Testing for both of the nodes not existing
	}
