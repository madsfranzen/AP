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
     * Adds a new key-value pair to the binary search tree or updates an existing key's value
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