public class LCA <Key extends Comparable<Key>, Value>{
	public Node root;
	
	public class Node{
		public Key key;
		public Value val;
		public Node left, right;
		public int N;
		
		public Node(Key key, Value val){
			this.val = val;
			this.key = key;
		}
	}
	
	
	
	//Is empty implementation
	public boolean isEmpty()
	{
		return size() == 0;
	}
	
	//size of tree implementation
	public int size() 
	{
		  return(size(root)); 
		}
		private int size(Node node) 
		{ 
		  if (node == null) return(0); 
		  else { 
		    return(size(node.left) + 1 + size(node.right)); 
		  } 
		} 
}