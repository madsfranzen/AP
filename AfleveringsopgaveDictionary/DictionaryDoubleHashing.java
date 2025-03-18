/**
 * Dictionary implementation using double hashing for collision resolution.
 * Uses lazy deletion with DELETED markers and maintains load factor ≤ 0.5.
 * 
 * Time Complexity:
 * - get/put/remove: O(1) average case with load factor ≤ 0.5
 * - get/remove: O(m) worst case where m is table size
 * - put: O(n) worst case when rehashing where n is number of elements
 * - size/isEmpty: O(1)
 */
public class DictionaryDoubleHashing<K, V> implements Dictionary<K, V> {
    private Entry<K, V>[] table;
    private int size;
    private final Entry<K, V> DELETED = new Entry<>(null, null);

    /**
     * @param length Initial capacity of the dictionary
     */
    public DictionaryDoubleHashing(int length) {
        table = new Entry[length];
        size = 0;
    }

    /**
     * @param key Key to look up
     * @return Value associated with key, or null if not found
     * @throws NullPointerException if key is null
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }

        int hash = hash(key);
        if (table[hash] != null && table[hash] != DELETED && table[hash].getKey().equals(key)) {
            return table[hash].getValue();
        }

        int step = hash2(key);
        int i = 1;
        int newHash = (hash + i * step) % table.length;

        while (i < table.length && table[newHash] != null) {
            if (table[newHash] != DELETED && table[newHash].getKey().equals(key)) {
                return table[newHash].getValue();
            }
            i++;
            newHash = (hash + i * step) % table.length;
        }

        return null;
    }

    /**
     * @return true if this dictionary contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param key Key to associate value with
     * @param value Value to store
     * @return Previous value for key, or null if none
     * @throws NullPointerException if key or value is null
     */
    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException("Key and value cannot be null");
        }

        if (size >= table.length * 0.5) {
            rehash();
        }

        int hash = hash(key);

        if (table[hash] != null && table[hash] != DELETED && table[hash].getKey().equals(key)) {
            V oldValue = table[hash].getValue();
            table[hash].value = value;
            return oldValue;
        }

        if (table[hash] == null || table[hash] == DELETED) {
            table[hash] = new Entry<>(key, value);
            size++;
            return null;
        }

        int i = 1;
        int step = hash2(key);
        int newHash = (hash + i * step) % table.length;

        while (i < table.length && table[newHash] != null) {
            if (table[newHash] != DELETED && table[newHash].getKey().equals(key)) {
                V oldValue = table[newHash].getValue();
                table[newHash].value = value;
                return oldValue;
            }
            i++;
            newHash = (hash + i * step) % table.length;
        }

        table[newHash] = new Entry<>(key, value);
        size++;
        return null;
    }

    /** Doubles table size and rehashes all entries */
    private void rehash() {
        Entry<K, V>[] oldTable = table;
        table = new Entry[table.length * 2 + 1];
        size = 0;
        for (Entry<K, V> entry : oldTable) {
            if (entry != null && entry != DELETED) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    /** Primary hash function */
    private int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    /** Secondary hash function: h'(key) = 7 - (key % 7) */
    private int hash2(K key) {
        return 7 - (Math.abs(key.hashCode()) % 7);
    }

    /**
     * @param key Key to remove
     * @return Value that was associated with key, or null if not found
     * @throws NullPointerException if key is null
     */
    @Override
    public V remove(K key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }

        int hash = hash(key);

        if (table[hash] != null && table[hash] != DELETED && table[hash].getKey().equals(key)) {
            V oldValue = table[hash].getValue();
            table[hash] = DELETED;
            size--;
            return oldValue;
        }

        int i = 1;
        int step = hash2(key);
        int newHash = (hash + i * step) % table.length;

        while (i < table.length && table[newHash] != null) {
            if (table[newHash] != DELETED && table[newHash].getKey().equals(key)) {
                V oldValue = table[newHash].getValue();
                table[newHash] = DELETED;
                size--;
                return oldValue;
            }
            i++;
            newHash = (hash + i * step) % table.length;
        }

        return null;
    }

    @Override
    public int size() {
        return size;
    }

    public void writeOut() {
        for (int i = 0; i < table.length; i++) {
            System.out.println(i + "\t" + table[i]);
        }
    }

    public static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "(" + key + " , " + value + ")";
        }
    }
}
