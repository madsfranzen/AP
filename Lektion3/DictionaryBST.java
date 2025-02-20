public class DictionaryBST<K extends Comparable<K>, V> implements
		Dictionary<K, V> {

	private Node root;

	public DictionaryBST() {
		root = null;
	}

	private class Node {
		private K key;
		private V value;
		private Node left;
		private Node right;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}

	@Override
	public V get(K key) {
		Node n = find(key);
		if (n == null) {
			return null;
		} else {
			return n.value;
		}
	}

	private Node find(K key) {
		Node current = root;
		boolean found = false;
		while (!found && current != null) {
			int d = current.key.compareTo(key);
			if (d == 0) {
				found = true;
			} else if (d > 0) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		if (found) {
			return current;
		} else {
			return null;
		}
	}

	@Override
	public boolean isEmpty() {
		if (size() == 0) {
			return true;
		} else
			return false;
	}

	/**
	 * Adds a new key-value pair to the binary search tree or updates an existing
	 * key's value
	 * 
	 * @param key   The key to add or update
	 * @param value The value to associate with the key
	 * @return The old value if key existed, null if it's a new entry
	 */
	@Override
	public V put(K key, V value) {
		// If tree is empty, create root node
		if (root == null) {
			root = new Node(key, value);
		} else {
			// Otherwise, recursively insert into the tree
			return put(key, value, root);
		}
		return null;
	}

	/**
	 * Helper method that recursively traverses the tree to insert or update a node
	 * 
	 * @param key    The key to add or update
	 * @param value  The value to associate with the key
	 * @param parent The current node being examined
	 * @return The old value if key existed, null if it's a new entry
	 */
	private V put(K key, V value, Node parent) {
		// If key already exists, update the value and return old value
		if (parent.key.equals(key)) {
			V oldValue = parent.value;
			parent.value = value;
			return oldValue;

		} else if (key.compareTo(parent.key) > 0) {
			// If new key is greater than parent key, go to right subtree
			if (parent.right == null) {
				// If right child is empty, create new node here
				parent.right = new Node(key, value);
			} else {
				// Otherwise, continue recursing down right subtree
				put(key, value, parent.right);
			}
			return null;
		} else {
			// If new key is less than parent key, go to left subtree
			if (parent.left == null) {
				// If left child is empty, create new node here
				parent.left = new Node(key, value);
			} else {
				// Otherwise, continue recursing down left subtree
				put(key, value, parent.left);
			}
			return null;
		}
	}

	@Override
	public V remove(K key) {
		V removedV = null;
		Node current = root;
		Node parent = null;

		// Find the node to remove
		while (current != null && current.key != key) {
			parent = current;
			if (key.compareTo(current.key) < 0) {
				current = current.left;
			} else {
				current = current.right;
			}
		}

		// If the node doesn't exist, return null (no removal)
		if (current == null) {
			return removedV;
		}

		// Node to be removed is found
		removedV = current.value;

		// Case 1: Node has no left child
		if (current.left == null) {
			if (parent == null) {
				// Removing the root node
				root = current.right;
			} else {
				if (key.compareTo(parent.key) < 0) {
					parent.left = current.right;
				} else {
					parent.right = current.right;
				}
			}
		}
		// Case 2: Node has a left child
		else if (current.right == null) {
			if (parent == null) {
				// Removing the root node
				root = current.left;
			} else {
				if (key.compareTo(parent.key) < 0) {
					parent.left = current.left;
				} else {
					parent.right = current.left;
				}
			}
		}
		// Case 3: Node has both children
		else {
			// Find the rightmost node in the left subtree
			Node parentOfRightMost = current;
			Node rightMost = current.left;
			while (rightMost.right != null) {
				parentOfRightMost = rightMost;
				rightMost = rightMost.right;
			}

			// Copy the rightmost node's value to the current node
			current.key = rightMost.key;
			current.value = rightMost.value;

			// Eliminate the rightmost node
			if (parentOfRightMost.right == rightMost) {
				parentOfRightMost.right = rightMost.left;
			} else {
				parentOfRightMost.left = rightMost.left;
			}
		}

		return removedV;
	}

	@Override
	public int size() {
		if (root == null) {
			return 0;
		}
		return 1 + size(root.left) + size(root.right);
	}

	public int size(Node root) {
		if (root == null) {
			return 0;
		}
		return 1 + size(root.left) + size(root.right);
	}

}