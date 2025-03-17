
@SuppressWarnings("unchecked")
public class DictionaryDoubleHashing<K, V> implements Dictionary<K, V> {
    private Entry<K, V>[] table;
    private int size;

    @SuppressWarnings("rawtypes")
    private final Entry DELETED = new Entry(null, null);

    public DictionaryDoubleHashing(int length) {
        table = new Entry[length];
        size = 0;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }

        int hash = hash(key);
        int step = hash2(key);

        // If key exists at first hash, return value
        if (table[hash] != null && table[hash].getKey().equals(key)) {
            return table[hash].getValue();
        }

        // If key does not exist at first hash, double hash
        int i = 1;
        int newHash = (hash + i * step) % table.length;

        while (table[newHash] != null && table[newHash] != DELETED && !table[newHash].getKey().equals(key)) {
            i++;
            newHash = (hash + i * step) % table.length;
        }

        if (table[newHash] != null && table[newHash] != DELETED) {
            return table[newHash].getValue();
        }

        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException("Key and value cannot be null");
        }

        // check if we need to rehash
        if (size >= table.length * 0.5) {
            rehash();
        }

        int hash = hash(key);
        int step = hash2(key);

        // If the key is already in the table, update the value
        if (table[hash] != null && table[hash].getKey().equals(key)) {
            V oldValue = table[hash].getValue();
            table[hash].value = value;
            return oldValue;
        }

        // If the key is not in the table, add it
        if (table[hash] == null || table[hash] == DELETED) {
            table[hash] = new Entry<>(key, value);
            size++;
            return null;
        }

        // Double hashing
        int i = 1;
        int newHash = (hash + i * step) % table.length;

        while (table[newHash] != null && table[newHash] != DELETED && !table[newHash].getKey().equals(key)) {
            i++;
            newHash = (hash + i * step) % table.length;
        }

        // If the key is in the table, update the value
        if (table[newHash] != null && table[newHash] != DELETED) {
            V oldValue = table[newHash].getValue();
            table[newHash].value = value;
            return oldValue;
        }

        // If the key is not in the table, add it
        table[newHash] = new Entry<>(key, value);
        size++;
        return null;
    }

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

    private int hash(K key) {
        return key.hashCode() % table.length;
    }

    private int hash2(K key) {
        return 7 - (key.hashCode() % 7);
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }

        int hash = hash(key);
        int step = hash2(key);

        if (table[hash] != null && table[hash].getKey().equals(key)) {
            V oldValue = table[hash].getValue();
            table[hash] = DELETED;
            size--;
            return oldValue;
        }

        int i = 1;
        int newHash = (hash + i * step) % table.length;

        while (table[newHash] != null && table[newHash] != DELETED && !table[newHash].getKey().equals(key)) {
            i++;
            newHash = (hash + i * step) % table.length;
        }

        if (table[newHash] != null && table[newHash] != DELETED) {
            V oldValue = table[newHash].getValue();
            table[newHash] = DELETED;
            size--;
            return oldValue;
        }

        return null;
    }

    @Override
    public int size() {
        return size;
    }

    // method only for test purpose
    public void writeOut() {
        for (int i = 0; i < table.length; i++) {
            System.out.println(i + "\t" + table[i]);
        }
    }

    public static class Entry<K, V> {
        @SuppressWarnings("FieldMayBeFinal")
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
