import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LCATest {

	@Test
	public void testIsEmpty() {
		LCA<Integer, Integer> test = new LCA<Integer, Integer>();
		boolean isEmpty = test.isEmpty();
		assertEquals(isEmpty, true);// new tree is empty, nothing added
		test.put(1, 2);
		test.put(2, 3);
		test.put(3, 4);
		test.put(4, 5);
		isEmpty = test.isEmpty();
		assertEquals(isEmpty, false);// not empty, as nodes were added

	}

	@Test
	public void testSize() {
		LCA<Integer, Integer> test = new LCA<Integer, Integer>();
		assertEquals(test.size(), 0);// new tree
		test.put(1, 3);
		assertEquals(test.size(), 1);// First element added
		test.put(2, 4);
		test.put(3, 5);
		test.put(4, 6);
		assertEquals(test.size(), 4);// Two more element added
		test.put(2, 8);
		assertEquals(test.size(), 4); // Size remains same as element already exists
	}

	@Test
	public void testGet() {
		LCA<Integer, Integer> LCA = new LCA<Integer, Integer>();
		assertEquals(false, LCA.present(5)); // Empty tree
		LCA.put(1, null);
		LCA.put(10, 1);
		LCA.put(5, 9);
		LCA.put(15, 2);
		LCA.put(9, 15);

		assertEquals("9", LCA.get(5).toString()); // Testing Left
		assertEquals("2", LCA.get(15).toString()); // Testing right then right
		assertEquals("15", LCA.get(9).toString()); // Testing tight then left
		assertEquals("1", LCA.get(10).toString()); // Testing root
	}

	@Test
	public void testPresent() {
		LCA<Integer, Integer> LCA = new LCA<Integer, Integer>();
		assertEquals(false, LCA.present(1)); // Not present
		LCA.put(7, 7);
		assertEquals(true, LCA.present(7)); // present
	}

	@Test
	public void testDelete() {
		LCA<Integer, Integer> LCA = new LCA<Integer, Integer>();
		LCA.delete(1);
		assertEquals("Deleting from empty tree", "()", LCA.printKeysInOrder());

		LCA.put(7, 7);                
		LCA.put(8, 8);   
		LCA.put(9, 9);
		LCA.put(3, 3);      
		LCA.put(1, 1);      
		LCA.put(2, 2);
		LCA.put(6, 6);
		LCA.put(4, 4);     
		LCA.put(5, 5);
		

		
		
		LCA.delete(10); assertEquals("(((()1(()2()))3((()4(()5()))6()))7(()8(()9())))", LCA.printKeysInOrder());
		//Deleting non present Node

		LCA.delete(5); assertEquals("(((()1(()2()))3((()4())6()))7(()8(()9())))", LCA.printKeysInOrder());
		//Deleting Leaf
		
		LCA.delete(8);
		assertEquals("(((()1(()2()))3((()4())6()))7(()9()))", LCA.printKeysInOrder());
		//Deleting node with one child
		
		LCA.delete(3);
		assertEquals("(((()1())2(()4(()6())))7(()9()))", LCA.printKeysInOrder());
		//Deleting node with two children ****
	
	}
	
	
	
	
}