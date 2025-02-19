import java.util.ArrayList;
import java.util.List;

import org.checkerframework.checker.units.qual.kN;

/**
 * BST is a binary search tree.
 * Note: Equal elements are not allowed in the tree.
 * Note: The methods add() and remove does not re-balance the tree.
 */

public class BST<E extends Comparable<E>> {
	private TreeNode root;
	private int size;

	/**
	 * Create a default BST with a natural order comparator
	 */
	public BST() {
		this.size = 0;
		this.root = null;
	}

	/**
	 * Insert element e into the binary tree
	 * Return true if the element is inserted successfully
	 * Return false if the element is found in the tree before insertion.
	 */
	public boolean insert(E e) {
		// Locate the node with the element e and its parent node.
		NodePair pair = locateNodeAndParent(e);
		boolean inserted = false;
		if (pair.current == null) {
			if (root == null) {
				root = new TreeNode(e); // Create a new root
			} else {
				TreeNode parent = pair.parent;
				// Create a new node and attach it to the parent node.
				if (e.compareTo(parent.element) < 0) {
					parent.left = new TreeNode(e);
				} else {
					parent.right = new TreeNode(e);
				}
			}
			inserted = true;
			size++;
		}
		return inserted; // Element inserted successfully
	}

	// Return a (current, parent)-pair consisting of
	// the node containing the element e and this node's parent.
	// Return value:
	// current != null and parent != null: current contains e and parent is the
	// parent to current
	// current != null and parent == null: current is the root containing e
	// current == null and parent != null: e is not found
	// and parent is the node where a node with e would be inserted as child
	// current == null and parent == null: the three is empty
	private NodePair locateNodeAndParent(E e) {
		boolean found = false;
		TreeNode parent = null;
		TreeNode current = root; // Start from the root
		while (!found && current != null) {
			if (e.compareTo(current.element) < 0) {
				parent = current;
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				parent = current;
				current = current.right;
			} else {
				found = true;
			}
		}
		return new NodePair(current, parent);
	}

	private class NodePair {
		private TreeNode current;
		private TreeNode parent;

		public NodePair(TreeNode current, TreeNode parent) {
			this.current = current;
			this.parent = parent;
		}
	}

	/**
	 * Returns true if the element is in the tree
	 */
	public boolean search(E e) {
		// Locate the node with the element e (and its parent node).
		NodePair pair = locateNodeAndParent(e);
		return pair.current != null;
	}

	/**
	 * Inorder traversal from the root
	 */
	public void inorder() {
		inorder(root);
	}

	/**
	 * Inorder traversal from a subtree
	 */
	private void inorder(TreeNode root) {
		TreeNode current = root;
		if (current.left != null) {
			inorder(current.left);
		}
		System.out.print(current.element + " ");
		if (current.right != null) {
			inorder(current.right);
		}
	}

	/**
	 * Postorder traversal from the root
	 */
	public void postorder() {
		postorder(root);
	}

	/**
	 * Postorder traversal from a subtree
	 */
	private void postorder(TreeNode root) {
		TreeNode current = root;
		if (current.left != null) {
			postorder(current.left);
		}
		if (current.right != null) {
			postorder(current.right);
		}
		System.out.print(current.element + " ");
	}

	/**
	 * Preorder traversal from the root
	 */
	public void preorder() {
		preorder(root);
	}

	/**
	 * Preorder traversal from the root
	 */
	public void preorder(TreeNode root) {
		TreeNode current = root;
		System.out.print(current.element + " ");
		if (current.left != null) {
			preorder(current.left);
		}
		if (current.right != null) {
			preorder(current.right);
		}
	}

	private class TreeNode {
		private E element;
		private TreeNode left;
		private TreeNode right;

		private TreeNode(E e) {
			element = e;
		}
	}

	/**
	 * Get the number of nodes in the tree
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Returns the root of the tree
	 */
	@SuppressWarnings("unused")
	private TreeNode getRoot() {
		return root;
	}

	/**
	 * Delete an element from the binary tree.
	 * Return true if the element is deleted successfully
	 * Return false if the element is not in the tree
	 */
	public boolean delete(E e) {
		// Locate the node with the element e and its parent node.
		NodePair pair = locateNodeAndParent(e);
		boolean found = true;
		if (pair.current == null) { // the element e is not in the tree
			found = false;
		} else {
			TreeNode current = pair.current;
			TreeNode parent = pair.parent; // may be null
			// Case 1: current has no left child
			if (current.left == null) {
				// Connect the parent with the right child of the current node
				if (parent == null) {
					root = current.right;
				} else {
					if (e.compareTo(parent.element) < 0)
						parent.left = current.right;
					else
						parent.right = current.right;
				}
			} else {
				// Case 2: The current node has a left child
				// Locate the rightmost(biggest) node in the left subtree of
				// the current node and also its parent
				TreeNode parentOfRightMost = current;
				TreeNode rightMost = current.left;

				if (rightMost.right == null) { // special case: no node to the right of rightMost
					current.element = rightMost.element;
					current.left = rightMost.left;
				} else {
					while (rightMost.right != null) {
						parentOfRightMost = rightMost;
						rightMost = rightMost.right; // keep going to the right
					}
					// Replace the element in current by the element in rightMost.
					current.element = rightMost.element;
					// Eliminate rightmost node.
					parentOfRightMost.right = rightMost.left;
				}

			}
			size--; // Reduce the size of the tree
		}
		return found; // Element deleted successfully
	}

	@Override
	public String toString() {
		return toString(root);
	}

	private String toString(TreeNode node) {
		if (node == null)
			return "";
		return toString(node.left) + " " + node.element + " " + toString(node.right);
	}

	// ----------------------- OPGAVE 2 ------------------------------

	/**
	 * Returns a list containing all elements in the binary search tree in inorder
	 * traversal.
	 * The elements are visited in ascending order: left subtree, root, right
	 * subtree.
	 * 
	 * @return an ArrayList containing all elements in the binary search tree in
	 *         inorder traversal
	 * @throws NullPointerException if the tree is empty (root is null)
	 */
	public List<E> inorderList() {
		List<E> list = new ArrayList<>();

		if (root.left != null) {
			inorderList(list, root.left);
		}

		list.add(root.element);

		if (root.right != null) {
			inorderList(list, root.right);

		}

		return list;
	}

	/**
	 * Helper method for the inorder traversal of the binary search tree.
	 * Recursively traverses the tree in inorder (left-root-right) and adds elements
	 * to the provided list.
	 *
	 * @param list The list to store the elements in inorder traversal
	 * @param node The current node being processed in the traversal
	 */
	private void inorderList(List<E> list, TreeNode node) {
		TreeNode current = node;

		if (current.left != null) {
			inorderList(list, current.left);
		}

		list.add(current.element);

		if (current.right != null) {
			inorderList(list, current.right);
		}

	}

	public int height() {
		int height = height(0, root);
		return height;
	}

	private int height(int height, TreeNode node) {
		TreeNode current = node;
		height++;

		if (current.left == null || current.right == null) {
			return height;
		}

		return Math.max(height(height, current.left), height(height, current.right));
	}

	public E findMax() {
		return findMax(root);
	}

	private E findMax(TreeNode node) {
		if (node.right != null) {
			return findMax(node.right);
		} else {
			return node.element;
		}
	}

	public E findMin() {
		return findMin(root);
	}

	private E findMin(TreeNode node) {
		if (node.left != null) {
			return findMin(node.left);
		} else {
			return node.element;
		}
	}

	public boolean removeMax() {
		if (root == null) {
			return false;
		}

		TreeNode current = root;
		TreeNode parent = null;
		while (current.right != null) {
			parent = current;
			current = current.right;
		}

		if (parent == null) {
			root = current.left;
		} else {
			parent.right = current.left;
		}
		
		size++;
		return true;
	}

	public boolean removeMin() {
		if (root == null) {
			return false;
		}

		TreeNode current = root;
		TreeNode parent = null;

		while (current.left != null) {
			parent = current;
			current = current.left;
		}

		if (parent == null) {
			root = current.right;
		}
		else {
			parent.left = current.right;
		}

		size--;
		return true;
	}

		public int greaterThanCountRecursive(E element) {
			return greaterThanCountRecursive(element, root);
		}

		private int greaterThanCountRecursive(E element, TreeNode node) {
		if (node == null) {
			return 0;
		}
		
		if (node.element.compareTo(element) > 0) {
			return 1 + greaterThanCountRecursive(element, node.right) + 
					greaterThanCountRecursive(element, node.left);
		} else {
			return greaterThanCountRecursive(element, node.right);
		}
	}

		public List<E> greaterThanRecursive(E element) {
			ArrayList<E> list = new ArrayList<>();
			greaterThanRecursive(element, root, list);
			return list;
		}

		private void greaterThanRecursive(E element, TreeNode node, List<E> list) {
		if (node == null) {
			return;
		}
		
		if (node.element.compareTo(element) > 0) {
			list.add(node.element);
			greaterThanRecursive(element, node.right, list);
			greaterThanRecursive(element, node.left, list);
		} else {
			greaterThanRecursive(element, node.right, list);
		}
	}
}