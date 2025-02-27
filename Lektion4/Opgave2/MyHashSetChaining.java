package Opgave2;

@SuppressWarnings("unchecked")
public class MyHashSetChaining<E> implements MySet<E> {
    // The number of elements in the set
    private int size = 0;

    // Hash table is an array with each cell that is a linked list
    private Node<E>[] table;

    public MyHashSetChaining(int bucketsLength) {
        table = (Node<E>[]) new Node[bucketsLength];
        size = 0;
    }

    /** Hash function */
    private int hash(int hashCode) {
        if (hashCode < 0) {
            hashCode = -hashCode;
        }
        return hashCode % table.length;
    }

    @Override /** Remove all elements from this set */
    public void clear() {
        int bucketsLength = table.length;
        table = (Node<E>[]) new Node[bucketsLength];
        size = 0;
    }

    @Override /** Return true if the element is in the set */
    public boolean contains(E e) {
        int bucketIndex = hash(e.hashCode());
        Node<E> current = table[bucketIndex];
        boolean found = false;
        while (!found && current != null) {
            if (current.data.equals(e)) {
                found = true;
            } else {
                current = current.next;
            }
        }
        return found;
    }

    @SuppressWarnings("rawtypes")
    @Override /** Add an element to the set */
    public boolean add(E e) {
        int bucketIndex = hash(e.hashCode());
        Node<E> current = table[bucketIndex];
        boolean found = false;
        while (!found && current != null) {
            if (current.data.equals(e)) {
                found = true;
                // Already in the set
            } else {
                current = current.next;
            }
        }
        if (!found) {
            Node newNode = new Node();
            newNode.data = e;
            newNode.next = table[bucketIndex];
            table[bucketIndex] = newNode;
            size++;
        }
        rehash();
        return !found;
    }

    @Override /** Remove the element from the set */
    public boolean remove(E e) {
        int bucketIndex = hash(e.hashCode());
        Node<E> current = table[bucketIndex];
        boolean found = false;
        while (!found && current != null) {
            if (current.data.equals(e)) {
                found = true;
            } else {
                current = current.next;
            }
        }
        if (found) {
            table[bucketIndex] = current.next;
            size--;
        }
        return found;
    }

    @Override /** Return true if the set contains no elements */
    public boolean isEmpty() {
        return size == 0;
    }

    @Override /** Return the number of elements in the set */
    public int size() {
        return size;
    }

    // method only for test purpose
    void writeOut() {
        for (int i = 0; i < table.length; i++) {
            Node<E> temp = table[i];
            if (temp != null) {
                System.out.print(i + "\t");
                while (temp != null) {
                    System.out.print(temp.data + "\t");
                    temp = temp.next;
                }
                System.out.println();
            }
        }
    }

    @SuppressWarnings("hiding")
    private class Node<E> {
        public E data;
        public Node<E> next;
    }

    private void rehash() {
        if (size / (table.length * 1.0) > 0.75) {
            Node<E>[] tableOld = table;
            table = (Node<E>[]) new Node[tableOld.length * 2 + 1];
            size = 0;
            for (int i = 0; i < tableOld.length; i++) {
                Node<E> current = tableOld[i];
                while (current != null) {
                    add(current.data);
                    current = current.next;
                }
            }
        }
    }
}
