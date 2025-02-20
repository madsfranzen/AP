public class DictionaryBST<K extends Comparable<K>, V> implements Dictionary<K, V> {

	// Root node of the binary search tree
	private Node root;

	// Constructor to initialize an empty tree
	public DictionaryBST() {
		root = null;
	}

	// Inner class representing a node in the binary search tree
	private class Node {
		private K key; // Key of the node
		private V value; // Value associated with the key
		private Node left; // Left child node
		private Node right; // Right child node

		// Constructor to create a new node with a key-value pair
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}

	// Method to get the value associated with a given key
	@Override
	public V get(K key) {
		Node n = find(key); // Find the node with the given key
		if (n == null) {
			return null; // Return null if key is not found
		} else {
			return n.value; // Return the value if key is found
		}
	}

	// Helper method to find a node with a given key
	private Node find(K key) {
		Node current = root; // Start from the root node
		boolean found = false; // Flag to indicate if the key is found
		while (!found && current != null) {
			int d = current.key.compareTo(key); // Compare the current node's key with the given key
			if (d == 0) {
				found = true; // Key is found
			} else if (d > 0) {
				current = current.left; // Move to the left child if the key is smaller
			} else {
				current = current.right; // Move to the right child if the key is larger
			}
		}
		if (found) {
			return current; // Return the node if key is found
		} else {
			return null; // Return null if key is not found
		}
	}

	// Method to check if the tree is empty
	@Override
	public boolean isEmpty() {
		if (size() == 0) {
			return true; // Return true if the tree is empty
		} else {
			return false; // Return false if the tree is not empty
		}
	}

	/**
	 * Inserts a key-value pair into the binary search tree.
	 * If the key already exists, the value is updated and the old value is
	 * returned.
	 * If the key is new, it creates a new node and returns null.
	 *
	 * @param key   The key to insert
	 * @param value The value to associate with the key
	 * @return The old value if the key existed, null otherwise
	 */
	@Override
	public V put(K key, V value) {
		// If the tree is empty, create a new root node
		if (root == null) {
			root = new Node(key, value);
			return null;
		}
		// Otherwise, start recursive insertion from the root
		return put(key, value, root);
	}

	/**
	 * Helper method that recursively traverses the tree to find the correct
	 * position to insert the new key-value pair or update an existing value.
	 *
	 * @param key    The key to insert
	 * @param value  The value to associate with the key
	 * @param parent The current node being examined
	 * @return The old value if the key existed, null otherwise
	 */
	private V put(K key, V value, Node parent) {
		// If we found a node with the same key, update its value
		if (parent.key.equals(key)) {
			return updateExistingValue(parent, value);
		}

		// Determine whether to go right or left based on key comparison
		boolean goRight = key.compareTo(parent.key) > 0;
		Node child = goRight ? parent.right : parent.left;

		// If we've reached a null child, this is where we insert the new node
		if (child == null) {
			if (goRight) {
				parent.right = new Node(key, value);
			} else {
				parent.left = new Node(key, value);
			}
			return null;
		} else {
			// Otherwise, continue searching in the appropriate subtree
			return put(key, value, child);
		}
	}

	/**
	 * Helper method to update the value of an existing node and return the old
	 * value.
	 *
	 * @param node     The node whose value needs to be updated
	 * @param newValue The new value to set
	 * @return The old value that was replaced
	 */
	private V updateExistingValue(Node node, V newValue) {
		V oldValue = node.value;
		node.value = newValue;
		return oldValue;
	}

	// Method to remove a key-value pair from the tree
	@Override
	public V remove(K key) {
		V removedV = null; // Variable to store the value of the removed node
		Node current = root; // Start from the root node
		Node parent = null; // Parent of the current node

		// Find the node to remove
		while (current != null && current.key != key) {
			parent = current;
			if (key.compareTo(current.key) < 0) {
				current = current.left; // Move to the left child if the key is smaller
			} else {
				current = current.right; // Move to the right child if the key is larger
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

	// Method to get the size of the tree (number of nodes)
	@Override
	public int size() {
		if (root == null) {
			return 0; // Return 0 if the tree is empty
		}
		return 1 + size(root.left) + size(root.right); // Return the size of the tree
	}

	// Helper method to get the size of a subtree
	public int size(Node root) {
		if (root == null) {
			return 0; // Return 0 if the subtree is empty
		}
		return 1 + size(root.left) + size(root.right); // Return the size of the subtree
	}

}