package Opgave2;

/**
 * This class implements a hash set using separate chaining.
 */
public class MyHashSetLinearProbing<E> implements MySet<E> {
    private E[] table;
    private int size;

    private final E DELETED = (E)new Object();
    /**
     * Constructs a hash table.
     *
     * @param bucketsLength the length of the buckets array
     */
    public MyHashSetLinearProbing(int bucketsLength) {
        table = (E[]) new Object[bucketsLength];
        size = 0;
    }

    private int hash(int hashCode) {
        if (hashCode < 0) {
            hashCode = -hashCode;
        }
        return hashCode % table.length;
    }

    @Override
    /** Return true if the element is in the set */
    public boolean contains(E e) {
        int hash = hash(e.hashCode());
        int startHash = hash;
        
        while (table[hash] != null) {
            if (table[hash] != DELETED && table[hash].equals(e)) {
                return true;
            }
            hash = (hash + 1) % table.length;
            if (hash == startHash) break;
        }
        return false;
    }

    @Override
    /** Remove all elements from this set */
    public void clear() {
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        size = 0;
    }

    /**
     * Add an element to the set.
     *
     * @return true if e is a new object, false if e was already in the set
     */
    public boolean add(E e) {
        if (contains(e)) {
            return false;
        }

        int hash = hash(e.hashCode());
        int startHash = hash;
        
        while (table[hash] != null && table[hash] != DELETED) {
            hash = (hash + 1) % table.length;
            if (hash == startHash) return false; // table is full
        }
        
        table[hash] = e;
        size++;
        return true;
    }

    /**
     * Remove the element from the set.
     *
     * @return true if e was removed from this set, false if e was not an
     * element of this set
     */
    public boolean remove(E e) {
        int hash = hash(e.hashCode());
        int startHash = hash;
        
        while (table[hash] != null) {
            if (table[hash] != DELETED && table[hash].equals(e)) {
                table[hash] = DELETED;  // Mark as deleted instead of null
                size--;
                return true;
            }
            hash = (hash + 1) % table.length;
            if (hash == startHash) break;
        }
        return false;
    }

    @Override
    /** Return the number of elements in the set */
    public int size() {
        return size;
    }

    @Override
    /** Return true if the set contains no elements */
    public boolean isEmpty() {
        return size == 0;
    }


    // method only for test purpose
    public void writeOut() {
        for (int i = 0; i < table.length; i++) {
            System.out.println(i + "\t" + table[i]);
        }
    }


}
