public class LCA<Key extends Comparable<Key>, Value> {
	public Node root;

	public class Node {
		public Key key;
		public Value val;
		public Node left, right;
		public int N;

		public Node(Key key, Value val) {
			this.val = val;
			this.key = key;
		}
	}

	// Is empty implementation
	public boolean isEmpty() {
		return size() == 0;
	}

	// size of tree implementation
	public int size() {
		return (size(root));
	}

	private int size(Node node) {
		if (node == null)
			return (0);
		else {
			return (size(node.left) + 1 + size(node.right));
		}
	}

	// get implementation, finds the key
	public Value get(Key key) {
		Node x = root;

		while (x != null) {

			int comp = key.compareTo(x.key);
			if (comp < 0) {
				x = x.left;
			} else if (comp > 0) {
				x = x.right;
			} else {
				return x.val;
			}
		}
		return null;
	}

	// put implementation, inserts values into tree
	public void put(Key key, Value val) {
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) {
		if (x == null) {
			return new Node(key, val);
		}
		int comp = key.compareTo(x.key);
		if (comp < 0) {
			x.left = put(x.left, key, val);
		} else if (comp > 0) {
			x.right = put(x.right, key, val);
		} else {
			x.val = val;
		}
		x.N = 1 + size(x.left) + size(x.right);
		return x;
	}

	// Node present in tree
	public boolean present(Key key) {
		return get(key) != null;
	}

	// Implementation of LCA
	public Key LCA(Node node, Key key1, Key key2) {
		if (node == null)
			return null;
		if (node.key == key1) {
			return node.key;
		}
		if (node.key == key2) {
			return node.key;
		}
		int cmp1 = node.key.compareTo(key1);
		int cmp2 = node.key.compareTo(key2);

		if (cmp1 >= 0 && cmp2 >= 0)
			return LCA(node.left, key1, key2);

		if (cmp1 <= 0 && cmp2 <= 0)
			return LCA(node.right, key1, key2);

		return node.key;
	}

	public void delete(Key key) {
		root = delete(root, key);
	}

	private Node delete(Node node, Key key) {
		if (node == null) {
			return null;
		}

		int compare = key.compareTo(node.key);

		if (compare > 0) {
			node.right = delete(node.right, key);
			node.left = delete(node.left, key);
		} else if (compare < 0) {
			node.left = delete(node.left, key);
		} else {
			if (node.right == null) {
				return node.left;
			}
			if (node.left == null) {
				return node.right;
			}
			Node temp = node;
			node = max(temp.left);
			node.left = deleteMax(temp.left);
			node.right = temp.right;
		}

		node.N = size(node.left) + size(node.right) + 1;
		return node;

	}

	private Node deleteMax(Node node) {
		if (node.right == null)
			return node.left;
		node.right = deleteMax(node.right);
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}

	public Node max(Node node) {
		if (node.right != null) {
			return max(node.right);
		}
		return node;
	}

	public String printKeysInOrder() {
		String res = "";
		if (isEmpty()) {
			return res += "()";
		} else {
			return res = printKeysInOrder(root);
		}

	}

	private String printKeysInOrder(Node node) {
		String res = "";
		if (node == null) {
			return res += "()";
		}

		else {
			return res += ("(" + printKeysInOrder(node.left) + node.key.toString() + printKeysInOrder(node.right)
					+ ")");
		}

	}

}